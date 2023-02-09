package tests

import api.wrapper.ProjectsApiWrapper
import com.makrol.teamcity.api.client.model.Project
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

class CallerCanCreatePipeline {
    private val projectApi = ProjectsApiWrapper()

    private lateinit var project: Project

    @Test
    fun createSimpleTeamCityProject() {
        project = runBlocking { projectApi.createProject() }
        assertNotNull(project)

        val createdProject = runBlocking { projectApi.getProject(project.name!!) }
        assertEquals(project, createdProject)
    }

    @AfterEach
    fun deleteProject() {
        runBlocking { projectApi.deleteProject(project) }
    }
}