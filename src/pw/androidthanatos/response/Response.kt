package pw.androidthanatos.response

import pw.androidthanatos.method.Method
import java.io.InputStream
import java.nio.charset.Charset

/**
 * 网络响应类
 */


interface Response {

    fun setHeads(heads: HashMap<String,String>): Response

    fun getHeads(): HashMap<String,String>

    fun setInputStream(stream: InputStream): Response

    fun getInputStream(): InputStream

    fun setResponseCode(code: Int): Response

    fun getResponseCode(): Int

    fun setBody(body: String): Response

    fun getBody(charset: Charset = Charset.forName("UTF-8")): String

    fun setContentLength(len : Long): Response

    fun getContentLength(): Long

    fun setContentType(type: String): Response

    fun getContentType(): String




}