package api.wrapper

import com.makrol.teamcity.api.client.api.UserApi
import com.makrol.teamcity.api.client.model.User
import helpers.appendRandomNumericPostfix

class UserApiWrapper : BaseApiClient() {
    private val userApi: UserApi = UserApi(host, baseClient)

    fun createUser(): User {
        val newUser = User(
            username = "userName".appendRandomNumericPostfix(),
            name = "name".appendRandomNumericPostfix(),
            email = "${("fakeMail".appendRandomNumericPostfix())}@example.com",
            password = "pass".appendRandomNumericPostfix()
        )

        return userApi.addUser(body = newUser)
    }

    fun getUser(userName: String): User {
        return userApi.getUser(userName)
    }

    fun deleteUser(user: User) {
        userApi.deleteUser(user.username!!)
    }
}