package com.ahmedmamdouh13.domain.interactor

import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.model.TaskDomain
import com.ahmedmamdouh13.domain.status.MyResult

interface ProjectInteractor {
   suspend fun addProject(title: String, startDate: String, endDate: String): MyResult<Long>
   suspend fun getProjects(): MyResult<List<ProjectDomainModel>>
   suspend fun addTask(key: Int,taskTitle: String, taskTag: String): MyResult<Long>
   suspend fun getTasks(id: Int): MyResult<List<TaskDomain>>
}