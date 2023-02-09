package tests

import api.wrapper.ProjectsApiWrapper
import com.makrol.teamcity.api.client.model.Project
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CallerCanCreatePipeline {
    private val projectApi = ProjectsApiWrapper()

    private lateinit var project: Project

    @Test
    fun createSimpleTeamCityProject() = runBlocking {
        project = projectApi.createProject()
        assertNotNull(project)

        val createdProject = projectApi.getProject(project.parentProjectName!!)
        assertEquals(project, createdProject)
    }

    @AfterEach
    fun deleteProject() = runBlocking {
        projectApi.deleteProject(project)
    }
}