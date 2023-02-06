package api.wrapper

import com.makrol.teamcity.api.client.api.ProjectApi
import com.makrol.teamcity.api.client.model.NewProjectDescription
import com.makrol.teamcity.api.client.model.Project
import org.openapitools.client.infrastructure.HttpResponse

class ProjectsApiWrapper : BaseApiClient() {
    private val projectApi: ProjectApi = ProjectApi(Companion.host, baseEngine, setupConfig)

    suspend fun createProject(): Project {
        val newProject = NewProjectDescription(name = "simple_tc_project".appendRandomNumericPostfix())

        return projectApi.addProject(newProject).body()
    }

    suspend fun getProject(projectName: String): HttpResponse<Project> {
        return projectApi.getProject(projectName)
    }

    suspend fun deleteProject(project: Project) {
        projectApi.deleteProject(project.id!!)
    }
}