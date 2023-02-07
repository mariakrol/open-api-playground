package api.wrapper

import configuration.ConfigurationProvider
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlin.math.abs
import kotlin.random.Random

abstract class BaseApiClient {
    protected val baseEngine = OkHttpEngine(OkHttpConfig())

    protected val setupConfig: (HttpClientConfig<*>) -> Unit = { c -> getClientConfig(c) }

    fun String.appendRandomNumericPostfix(separator: String = "_"): String {
        return "${this}${separator}${abs(Random.nextInt())}"
    }

    private fun getClientConfig(config: HttpClientConfig<*>) {
        config.install(DefaultRequest) {
            header("Content-Type", "application/json")
        }
        config
            .install(Auth) {
                bearer {
                    loadTokens { BearerTokens(ConfigurationProvider.token, "") }
                }
            }

        config.install(ContentNegotiation) {
            json()
        }
    }

    companion object {
        const val host = ConfigurationProvider.teamCityHost
    }
}