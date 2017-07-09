package pw.androidthanatos.response

import java.io.InputStream
import java.nio.charset.Charset

/**
 * 真正的网络响应类
 */
class RealResponse : Response{

    private lateinit var heads: HashMap<String,String>

    private lateinit var stream: InputStream

    private lateinit var body: String

    private var code: Int = -1

    private var len : Long = -1

    private lateinit var type : String

    override fun setHeads(heads: HashMap<String, String>): Response {
        this.heads = heads
        return this
    }

    override fun getHeads(): HashMap<String, String> = this.heads

    override fun setInputStream(stream: InputStream): Response {
        this.stream = stream
        return this
    }

    override fun getInputStream(): InputStream = this.stream

    override fun setResponseCode(code: Int): Response {
        this.code = code
        return this
    }

    override fun getResponseCode(): Int = this.code

    override fun setBody(body: String): Response {
        this.body = body
        return this
    }

    override fun getBody(charset: Charset): String = String(this.body.toByteArray(),charset)


    override fun setContentLength(len: Long): Response {
        this.len = len
        return this
    }

    override fun getContentLength(): Long = this.len

    override fun setContentType(type: String): Response {
        this.type = type
        return this
    }

    override fun getContentType(): String = this.type

    class Builder{

        fun build(): Response = RealResponse()
    }
}