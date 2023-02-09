package api.wrapper

import configuration.ConfigurationProvider
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*

abstract class BaseApiClient {
    protected val setupConfig: (HttpClientConfig<*>) -> Unit = { config -> getClientConfig(config) }

    private fun getClientConfig(config: HttpClientConfig<*>) {
        config.install(DefaultRequest) {
            header("Content-Type", "application/json")
        }
        config
            .install(Auth) {
                bearer {
                    loadTokens { BearerTokens(ConfigurationProvider.token, refreshToken = "") }
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