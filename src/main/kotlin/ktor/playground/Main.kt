package ktor.playground

import com.makrol.teamcity.api.client.model.User
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun main() {
    val client = HttpClient(OkHttp) {
        install(Auth) {
            bearer {
                loadTokens {
                    BearerTokens(
                        "eyJ0eXAiOiAiVENWMiJ9.bHc4TEtQdVBaWnZWWHl1SjNTcHFmTXkycGRB.ZThjOGY3YTAtZmNjMS00MmJhLThlNTktNTUxOGQ2YTliNTc2",
                        ""
                    )
                }
            }
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }

    val user = User(username = "user#1", name = "user#1", password = "qwrqwe")
    val postUserResponse = client.post("http://lebkuchenhaus:8111/app/rest/users") {
        setBody(user)
        contentType(ContentType.Application.Json)
    }
    println(postUserResponse.bodyAsText())

    client.close()
}