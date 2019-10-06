package com.ahmedmamdouh13.duration.domain.interactor

import com.ahmedmamdouh13.duration.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.status.Status

interface ProjectInteractor {
   suspend fun addProject(title: String, startDate: String, endDate: String): Status
   suspend fun getProjects(): MyResult<List<ProjectDomainModel>>
}