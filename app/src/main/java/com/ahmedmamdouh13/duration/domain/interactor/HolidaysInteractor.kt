package com.ahmedmamdouh13.duration.domain.interactor

import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain

interface HolidaysInteractor {
   suspend fun getHolidaysFoundInLocation(location: String): MyResult<List<HolidaysDomain>>
    suspend fun getLocalHolidays() : List<HolidaysDomain>
}