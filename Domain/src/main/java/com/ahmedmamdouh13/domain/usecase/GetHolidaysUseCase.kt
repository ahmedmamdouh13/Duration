package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.model.HolidaysDomain

class GetHolidaysUseCase(private val repo: Repository) :
    HolidaysInteractor {
    override suspend fun getLocalHolidays(): List<HolidaysDomain> = repo.getHolidaysLocally()


    override suspend fun getHolidaysFoundInLocation(location: String): MyResult<List<HolidaysDomain>>
            = repo.getHolidayList(location)


}