package com.ahmedmamdouh13.duration.domain.usecase

import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.Repository
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain

class GetHolidaysUseCase(private val repo:Repository) : HolidaysInteractor{
    override suspend fun getLocalHolidays(): List<HolidaysDomain> = repo.getHolidaysLocally()


    override suspend fun getHolidaysFoundInLocation(location: String): MyResult<List<HolidaysDomain>>
            = repo.getHolidayList(location)


}