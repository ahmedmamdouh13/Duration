package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.model.TaskDomain
import com.ahmedmamdouh13.domain.status.MyResult


class AddProjectUseCase(private val repo: Repository) :
    ProjectInteractor {
    override suspend fun addProject(title: String, startDate: String, endDate: String): MyResult<Long> {
        return repo.addProject(title, startDate, endDate)
    }

    override suspend fun getProjects(): MyResult<List<ProjectDomainModel>> {
        return repo.getProjectsLocally()
    }

    override suspend fun addTask(key: Int, taskTitle: String, taskTag: String): MyResult<Long> {
        return repo.addTask(key,taskTitle, taskTag)
    }

    override suspend fun getTasks(id: Int): MyResult<List<TaskDomain>> {
        return repo.getTasks(id)
    }

}