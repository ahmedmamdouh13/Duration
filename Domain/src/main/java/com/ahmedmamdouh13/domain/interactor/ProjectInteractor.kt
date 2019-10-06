package com.ahmedmamdouh13.domain.interactor

import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status

interface ProjectInteractor {
   suspend fun addProject(title: String, startDate: String, endDate: String): Status
   suspend fun getProjects(): MyResult<List<ProjectDomainModel>>
}