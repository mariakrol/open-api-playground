package tests

import api.wrapper.UserApiWrapper
import com.makrol.teamcity.api.client.model.User
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CallerCanCreateUserByApiTests {
    private val userApi: UserApiWrapper = UserApiWrapper()

    private lateinit var user: User

    @Test
    fun createUser() {
        user = userApi.createUser()
        assertNotNull(user)

        val createdUser = userApi.getUser(user.username!!)
        assertEquals(createdUser, user)
    }

    @AfterEach
    fun deleteUser() {
        userApi.deleteUser(user)
    }
}