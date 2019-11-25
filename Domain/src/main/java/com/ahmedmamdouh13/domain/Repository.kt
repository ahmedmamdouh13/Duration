package com.ahmedmamdouh13.domain

import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.model.TaskDomain
import com.ahmedmamdouh13.domain.status.MyResult

interface Repository {
    suspend fun getHolidayList(s: String): MyResult<List<HolidaysDomain>>
    suspend fun getHolidaysLocally(): List<HolidaysDomain>
    suspend fun addProject(title: String, startDate: String, endDate: String): MyResult<Long>
    suspend fun getProjectsLocally(): MyResult<List<ProjectDomainModel>>
    suspend fun addTask(
        projectKey: Int,
        taskTitle: String,
        taskTag: String,
        projectTitle: String
    ): MyResult<Long>

    suspend fun getTasks(id: String): MyResult<List<TaskDomain>>
    suspend fun getProjectById(id: Int): MyResult<ProjectDomainModel>
}
