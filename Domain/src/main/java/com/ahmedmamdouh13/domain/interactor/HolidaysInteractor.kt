package com.ahmedmamdouh13.domain.interactor

import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.model.HolidaysDomain

interface HolidaysInteractor {
   suspend fun getHolidaysFoundInLocation(location: String): MyResult<List<HolidaysDomain>>
    suspend fun getLocalHolidays() : List<HolidaysDomain>
}