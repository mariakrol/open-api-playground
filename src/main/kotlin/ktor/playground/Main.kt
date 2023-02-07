package ktor.playground

import api.wrapper.ContentTypeInterceptor
import com.makrol.teamcity.api.client.model.User
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

suspend fun main() {
    val client = HttpClient(OkHttp) {
        engine {
            addInterceptor(ContentTypeInterceptor())
        }
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

    //val getUserResponse: HttpResponse = client.get("http://lebkuchenhaus:8111/app/rest/users?locator=admin")
    //println(response.bodyAsText())


    val user = User(
        username = "usr1231231",
        name = "name123123",
        password = "qwrqwe"
    )
    val postUserResponse = client.post("https://localhost/app/rest/users") {
        setBody(user)
    }
    println(postUserResponse.bodyAsText())

    client.close()
}