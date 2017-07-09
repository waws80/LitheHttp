package pw.androidthanatos.call

import pw.androidthanatos.response.RealResponse
import pw.androidthanatos.response.Response
import java.io.InputStream

/**
 * Created by liuxiongfei on 2017/7/7.
 */
class Call private constructor(private val response: String){

    private lateinit var body: String

    private lateinit var stream: InputStream

    private val heads :HashMap<String,String> by lazy { HashMap<String,String>() }

    private var code : Int = -1

    private var len : Long = -1

    private lateinit var type : String

    init {
       parseResponse()
    }

    fun call(): Response {
        val response = RealResponse.Builder().build()
        response.setBody(this.body)
        response.setInputStream(this.stream)
        response.setHeads(this.heads)
        response.setResponseCode(this.code)
        response.setContentLength(this.len)
        response.setContentType(this.type)
        return response
    }

    class Builder{

        fun getCall(stream: String) = Call(stream)
    }

    fun parseResponse(){
        val data = this.response.split("\r\n\r\n")
        if (data.isNotEmpty() && data.size == 2){//获取数据成功

            this.body = data[1]
            this.stream = data[1].byteInputStream()

            parseResponseHead(data[0])

        }else{//获取数据失败
            val error = "发生了未知错误"
            this.body = error
            this.stream = error.byteInputStream()
        }
    }

    private fun parseResponseHead(head: String) {

        head.split("\r\n").forEach {

            if (it.contains(":")){
                val res_head = it.split(":")
                heads.put(res_head[0].trim(),res_head[1].trim())
                if (res_head[0].trim() == "Content-Length"){
                    this.len = res_head[1].trim().toLong()
                }
                if (res_head[0].trim() == "Content-Type"){
                    this.type = res_head[1].trim()
                }
            }else{
                if (it.trim().startsWith("HTTP/1.")){
                    this.code = it.substring(9,12).toInt()

                }
            }
        }

    }
}