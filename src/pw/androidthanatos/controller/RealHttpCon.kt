package pw.androidthanatos.controller

import pw.androidthanatos.defaultsslsocketfactory.DefaultSSLSocketFactory
import pw.androidthanatos.message.Message
import pw.androidthanatos.method.Method
import pw.androidthanatos.request.Request
import java.io.InputStream
import java.net.InetAddress
import java.net.Socket

/**
 * 网络访问对象
 */
class RealHttpCon {

    fun request(request: Request): String{
        val ia = InetAddress.getByName(request.getHost())
        val socket = Socket(ia,80)
        val builder = Message.build().setRequestLine(method = request.getMethod().name,path = request.getPath())
                .setRequestHost(request.getHost())
                .addRequestHead("Connection","Close")
        request.getHead().forEach {
            builder.addRequestHead(it.key,it.value)
        }
        builder.setRequestHeadEnd()
        if (request.getMethod() != Method.GET){
            builder.addRequestBody(String(request.getBody()))
        }
        val osw = builder.builder(socket).put()

        val response = String(socket.getInputStream().use { it.readBytes() })
        if (socket.isConnected){
            osw.close()
            socket.close()
        }
        return  response
    }
}