package api.wrapper

import com.makrol.teamcity.api.client.api.ProjectApi
import com.makrol.teamcity.api.client.model.NewProjectDescription
import com.makrol.teamcity.api.client.model.Project
import helpers.appendRandomNumericPostfix

class ProjectsApiWrapper : BaseApiClient() {
    private val projectApi: ProjectApi = ProjectApi(host, baseClient)

    fun createProject(): Project {
        val newProject = NewProjectDescription(name = "simple_tc_project".appendRandomNumericPostfix())

        return projectApi.addProject(newProject)
    }

    fun getProject(projectName: String): Project {
        return projectApi.getProject(projectName)
    }

    fun deleteProject(project: Project) {
        projectApi.deleteProject(project.id!!)
    }
}