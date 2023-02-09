package tests

import api.wrapper.UserApiWrapper
import com.makrol.teamcity.api.client.model.User
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CallerCanCreateUserByApiTests {
    private val userApi = UserApiWrapper()

    private lateinit var user: User

    @Test
    fun createUser() {
        user = runBlocking { userApi.createUser() }
        assertNotNull(user)

        val createdUser = runBlocking { userApi.getUser(user.username!!) }
        assertEquals(createdUser, user)
    }

    @AfterEach
    fun deleteUser() {
        runBlocking {
            userApi.deleteUser(user)
        }
    }
}