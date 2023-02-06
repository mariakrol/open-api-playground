package api.wrapper

import com.makrol.teamcity.api.client.api.UserApi
import com.makrol.teamcity.api.client.model.User

class UserApiWrapper : BaseApiClient() {
    private val userApi = UserApi(Companion.host, baseEngine, setupConfig)

    suspend fun createUser(): User {
        val newUser = User(
            username = "userName".appendRandomNumericPostfix(),
            name = "name".appendRandomNumericPostfix(),
            email = "${("fakeMail".appendRandomNumericPostfix())}@example.com",
            password = "pass".appendRandomNumericPostfix()
        )

        return userApi.addUser(body = newUser).body()
    }

    suspend fun getUser(userName: String): User {
        return userApi.getUser(userName).body()
    }

    suspend fun deleteUser(user: User) {
        userApi.deleteUser(user.username!!)
    }
}