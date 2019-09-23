package com.ahmedmamdouh13.duration

import com.ahmedmamdouh13.duration.data.entity.Date
import com.ahmedmamdouh13.duration.data.entity.Holiday
import com.ahmedmamdouh13.duration.data.entity.Holidays
import com.ahmedmamdouh13.duration.data.entity.Response
import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.google.common.net.MediaType.*
import kotlinx.coroutines.CompletableDeferred
import okhttp3.MediaType
import okhttp3.ResponseBody

object Given {
    val locationDomainGiven = "EG"

    val name1 = "Oct"
    val desc1 = "October Victory"
    val date = "6/10/2019"
    val element1 = HolidaysDomain(name1, date, locationDomainGiven, desc1)
    val name2 = "Mawled"
    val desc2 = "Mawled nabawy"
    val date1 = "6/11/2019"
    val element2 = HolidaysDomain(name2, date1, locationDomainGiven, desc2)
    val name3 = "Eid Adha"
    val desc3 = "Eid Adha Celebration"
    val date2 = "24/8/2019"
    val element3 = HolidaysDomain(name3, date2, locationDomainGiven, desc3)
    val listDomainModel = listOf(element1, element2, element3)
    val statusDomainModelList:ArticleStatus<List<HolidaysDomain>> = ArticleStatus(listDomainModel)

    val holidaysResponse =retrofit2.Response.success(Holidays())
    val holyday1 = Holiday()
    val holyday2 = Holiday()
    val holyday3 = Holiday()

    val errorHolidaysResponse = retrofit2.Response.error<Holidays>(400, ResponseBody.create(
        MediaType.parse(ANY_TEXT_TYPE.subtype()),""))

    val deferredHolidaysError = CompletableDeferred(errorHolidaysResponse)
    val deferredHolidaysSuccess = CompletableDeferred(holidaysResponse)
    init {
            holidaysResponse.body()!!.response = Response()
        holyday1.name = name1
        holyday2.name = name2
        holyday3.name = name3
        holyday1.description = desc1
        holyday2.description = desc2
        holyday3.description = desc3
        holyday1.locations = locationDomainGiven
        holyday2.locations = locationDomainGiven
        holyday3.locations = locationDomainGiven
        holyday1.date = Date()
        holyday2.date = Date()
        holyday3.date = Date()
        holyday1.date!!.iso = date
        holyday2.date!!.iso = date1
        holyday3.date!!.iso = date2
            holidaysResponse.body()!!.response!!.holidays = listOf(holyday1, holyday2, holyday3)
    }
}