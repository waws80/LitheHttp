package pw.androidthanatos.test

import pw.androidthanatos.call.Call
import pw.androidthanatos.lithehttp.LitheHttpClient
import pw.androidthanatos.request.Request
import pw.androidthanatos.response.Response

/**
 * Created by liuxiongfei on 2017/7/6.
 */


fun main(args: Array<String>) {

    val request = Request.RealBuilder().setUrl("https://www.baidu.com").build()

    val call: Call = LitheHttpClient.getDefaultClient().addRequest(request)

    val response: Response = call.call()

    if (response.getResponseCode() == 200){

        println("我是请求到的头部：")
        response.getHeads().forEach {
            print("\n  键：${it.key}  值：${it.value}")
        }

        println("\r\n\r\n我是文本长度：${response.getContentLength()}\n")
        println("我是文本类型：${response.getContentType()}")
        print("\r\n\r\n我是请求到的内容：\r\n\r\n"+response.getBody())
    }
}