package api.wrapper

import com.makrol.teamcity.api.client.api.ProjectApi
import com.makrol.teamcity.api.client.model.NewProjectDescription
import com.makrol.teamcity.api.client.model.Project

class ProjectsApiWrapper : BaseApiClient() {
    private val projectApi: ProjectApi = ProjectApi(Companion.host, httpClientConfig = setupConfig)

    suspend fun createProject(): Project {
        val newProject = NewProjectDescription(name = "simple_tc_project".appendRandomNumericPostfix())

        return projectApi.addProject(newProject).body()
    }

    suspend fun getProject(projectName: String): Project {
        return projectApi.getProject(projectName).body()
    }

    suspend fun deleteProject(project: Project) {
        projectApi.deleteProject(project.id!!)
    }
}