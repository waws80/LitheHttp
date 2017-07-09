package pw.androidthanatos.request

import pw.androidthanatos.controller.RealHttpCon
import pw.androidthanatos.method.Method
import pw.androidthanatos.response.Response
import javax.swing.plaf.ButtonUI

/**
 * 真正的网络请求体
 */
class Request private constructor(private val url: String,
                           private val method: Method,
                           private val head: HashMap<String,String>,
                           private val body: ByteArray,
                           private val connTimeOut : Int,
                           private val readTimeOut : Int): IRequest {

    class RealBuilder : Builder{

        private lateinit var url: String

        private var method: Method = Method.GET

        private val heads: HashMap<String, String> by lazy { HashMap<String,String>() }

        private var body: ByteArray = ByteArray(0)

        private var connTimeOut = 6000

        private var readTimeOut = 6000

        override fun setUrl(url: String): Builder {
            this.url = url
            return this
        }

        override fun setMethod(method: Method): Builder {
            this.method = method
            return this
        }


        override fun addHead(key: String, value: String): Builder {
            this.heads.put(key, value)
            return this
        }

        override fun setBody(body: ByteArray): Builder {
            this.body = body
            return this
        }


        override fun setConnTimeOut(mill: Int): Builder {
            this.connTimeOut = mill
            return this
        }

        override fun setReadTimeOut(mill: Int): Builder {
            this.readTimeOut = mill
            return this
        }

        override fun build()
                = Request(
                this.url,
                this.method,
                this.heads,
                this.body,
                this.connTimeOut,
                this.readTimeOut)

    }


    override fun getHost() = parseUrl(this.url)[0]

    override  fun getPath() = parseUrl(this.url)[1]

    override fun getConnecTimeOut() = this.connTimeOut

    override fun getReadTimeOut() = this.readTimeOut

    override fun getMethod(): Method = this.method

    override fun getHead(): HashMap<String, String> = this.head

    override fun getBody(): ByteArray = this.body


    private fun parseUrl(url: String): Array<String>{
        val end = url.split("://")[1]
        var a =""
        var b = "/"
        if (end.contains("/")){
            a = end.split("/")[0]
            b = end.replace(a,"")
        }else {
            a = end
        }
        return arrayOf(a,b)
    }

}