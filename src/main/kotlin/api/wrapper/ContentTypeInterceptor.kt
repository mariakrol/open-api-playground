package api.wrapper

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ContentTypeInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {
        return this.newBuilder()
            .header("Content-Type", "application/json")
            .build()
    }
}