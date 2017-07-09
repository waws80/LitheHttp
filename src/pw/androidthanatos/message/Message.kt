package pw.androidthanatos.message

import pw.androidthanatos.method.Method
import java.io.OutputStreamWriter
import java.net.Socket


/**
 * message builder
 * 报文封装类
 */
internal class Message private constructor( private val socket: Socket,
                                            private val msg: String){

    companion object {
        private val INSTANCE = Builder()
        fun build() = INSTANCE
    }

    class Builder{

        private val buffer: StringBuilder by lazy {
            StringBuilder()
        }

        /**
         * 设置请求行
         * @param method 请求方法
         * @param path 请求的相对路径
         * @param protocol 请求协议版本
         */
        fun setRequestLine(method: String = Method.GET.name,
                           path: String = "/",
                           protocol: String = "HTTP/1.1"): Builder{
            buffer.append("$method $path $protocol\r\n")
            return this
        }


        /**
         * 设置请求主机
         * @param host 请求的主机名
         */
        fun setRequestHost(host: String): Builder{
            buffer.append("Host: $host\r\n")
            return this
        }

        /**
         * 添加请求头
         * @param key 请求头的键
         * @param value 请求头的值
         */
        fun addRequestHead(key: String, value: String): Builder{
            buffer.append("$key: $value\r\n")
            return this
        }

        /**
         * 设置请求头结束
         */
        fun setRequestHeadEnd(): Builder{
            buffer.append("\r\n")
            return this
        }

        /**
         * 设置请求体
         */
        fun addRequestBody(body: String): Builder{
            buffer.append(body)
            return this
        }

        /**
         * 创建报文对象
         */
        fun builder(socket: Socket) = Message(socket,buffer.toString())


    }


    fun put(): OutputStreamWriter{
        val osw = OutputStreamWriter(socket.getOutputStream())
        println("-------报文开始-------\r\n\r\n$msg -------报文结束-------\r\n")
        osw.write(msg)
        osw.flush()
        socket.shutdownOutput()
        return osw
    }
}