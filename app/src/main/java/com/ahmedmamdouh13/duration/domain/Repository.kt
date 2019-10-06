package com.ahmedmamdouh13.duration.domain

import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.duration.domain.status.Status

interface Repository {
   suspend fun getHolidayList(s: String) : MyResult<List<HolidaysDomain>>
    suspend fun getHolidaysLocally() : List<HolidaysDomain>
    suspend fun addProject(title: String, startDate: String, endDate: String): Status
    suspend fun getProjectsLocally():MyResult<List<ProjectDomainModel>>

}
