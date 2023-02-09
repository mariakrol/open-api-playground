package api.wrapper

import configuration.ConfigurationProvider
import kotlin.math.abs
import kotlin.random.Random

open class BaseApiClient {
    fun String.appendRandomNumericPostfix(separator: String = "_"): String {
        return "${this}${separator}${abs(Random.nextInt())}"
    }

    companion object {
        const val host = ConfigurationProvider.teamCityHost
    }
}