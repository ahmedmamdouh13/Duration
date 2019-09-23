package com.ahmedmamdouh13.duration.domain.interactor

import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain

interface HolidaysInteractor {
   suspend fun getHolidays(): List<HolidaysDomain>
   suspend fun getHolidaysFoundInLocation(location: String): ArticleStatus<List<HolidaysDomain>>
}