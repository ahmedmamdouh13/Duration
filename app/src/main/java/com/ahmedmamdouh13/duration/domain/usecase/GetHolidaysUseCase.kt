package com.ahmedmamdouh13.duration.domain.usecase

import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain

class GetHolidaysUseCase(private val repo:ArticleRepository) : HolidaysInteractor{
    override suspend fun getLocalHolidays(): List<HolidaysDomain> = repo.getHolidaysLocally()


    override suspend fun getHolidaysFoundInLocation(location: String): ArticleStatus<List<HolidaysDomain>>
            = repo.getHolidayList(location)


}