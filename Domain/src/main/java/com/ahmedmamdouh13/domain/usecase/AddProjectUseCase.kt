package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status


class AddProjectUseCase(private val repo: Repository) :
    ProjectInteractor {
    override suspend fun addProject(title: String, startDate: String, endDate: String): Status {
        return repo.addProject(title, startDate, endDate)
    }

    override suspend fun getProjects(): MyResult<List<ProjectDomainModel>> {
        return repo.getProjectsLocally()
    }

}