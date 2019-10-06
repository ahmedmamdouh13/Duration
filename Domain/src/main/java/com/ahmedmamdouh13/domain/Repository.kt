package com.ahmedmamdouh13.domain

import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.status.Status

interface Repository {
   suspend fun getHolidayList(s: String) : MyResult<List<HolidaysDomain>>
    suspend fun getHolidaysLocally() : List<HolidaysDomain>
    suspend fun addProject(title: String, startDate: String, endDate: String): Status
    suspend fun getProjectsLocally(): MyResult<List<ProjectDomainModel>>

}
