package tests

import api.wrapper.ProjectsApiWrapper
import com.makrol.teamcity.api.client.model.Project
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CallerCanCreatePipeline {
    private val projectApi = ProjectsApiWrapper()

    private lateinit var project: Project

    @Test
    fun createSimpleTeamCityProject() {
        project = projectApi.createProject()
        assertNotNull(project)

        val createdProject = projectApi.getProject(project.parentProjectName!!)
        assertEquals(project, createdProject)
    }

    @AfterEach
    fun deleteProject() {
        projectApi.deleteProject(project)
    }
}