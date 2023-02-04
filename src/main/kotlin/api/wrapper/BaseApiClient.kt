package api.wrapper

import configuration.ConfigurationProvider
import okhttp3.OkHttpClient
import kotlin.math.abs
import kotlin.random.Random

open class BaseApiClient {
    protected fun String.appendRandomNumericPostfix(separator: String = "_"): String {
        return "${this}${separator}${abs(Random.nextInt())}"
    }

    companion object {
        const val host = ConfigurationProvider.teamCityHost
        val baseClient: OkHttpClient = getApiClient()

        private fun getApiClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .build()
        }
    }
}