package api.wrapper

import api.wrapper.interceptors.AuthorizationInterceptor
import api.wrapper.interceptors.ContentTypeInterceptor
import configuration.ConfigurationProvider
import okhttp3.Interceptor
import okhttp3.OkHttpClient

open class BaseApiClient {
    fun String.appendRandomNumericPostfix(separator: String = "_"): String {
        return "${this}${separator}${abs(Random.nextInt())}"
    }

    companion object {
        const val host = ConfigurationProvider.teamCityHost
        val baseClient: OkHttpClient = getApiClient(AuthorizationInterceptor(), ContentTypeInterceptor())

        private fun getApiClient(vararg interceptors: Interceptor): OkHttpClient {
            val builder = OkHttpClient.Builder()

            for (interceptor in interceptors) {
                builder.addInterceptor(interceptor)
            }

            return builder.build()
        }
    }
}