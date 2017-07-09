package pw.androidthanatos.lithehttp

import pw.androidthanatos.call.Call
import pw.androidthanatos.controller.RealHttpCon
import pw.androidthanatos.request.Request

/**
 * LitheHttpClient
 * @author thanatos
 */
class LitheHttpClient {

    companion object{
        /*全局唯一实例化对象*/
        private val INSTANCE = LitheHttpClient()

        fun getDefaultClient() = INSTANCE
    }


    fun addRequest(request: Request) = Call.Builder().getCall(execute(request))

    private fun execute(request: Request) = RealHttpCon().request(request)

}