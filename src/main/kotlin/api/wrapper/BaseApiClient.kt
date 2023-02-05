package api.wrapper

import configuration.ConfigurationProvider
import okhttp3.OkHttpClient
import kotlin.math.abs
import kotlin.random.Random

open class BaseApiClient {
    fun String.appendRandomNumericPostfix(separator: String = "_"): String {
        return "${this}${separator}${abs(Random.nextInt())}"
    }

    companion object {
        const val host = ConfigurationProvider.teamCityHost
        val baseClient: OkHttpClient = getApiClient(AuthorizationInterceptor())

        private fun getApiClient(authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(authorizationInterceptor)
                .build()
        }
    }
}