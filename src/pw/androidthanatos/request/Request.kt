package pw.androidthanatos.request

import pw.androidthanatos.method.Method

/**
 * LitheHttpClient 请求体
 */
interface Builder {


    fun setUrl(url: String): Builder

    fun setMethod(method: Method = Method.GET): Builder

    fun addHead(key: String , value: String): Builder

    fun setBody(body: ByteArray): Builder

    fun setConnTimeOut(mill: Int): Builder

    fun setReadTimeOut(mill: Int): Builder

    fun build(): Request
}

interface IRequest {

    fun getHost(): String

    fun getPath(): String

    fun getConnecTimeOut(): Int

    fun getReadTimeOut(): Int

    fun getMethod(): Method

    fun getHead(): HashMap<String,String>

    fun getBody(): ByteArray
}