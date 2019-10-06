package com.ahmedmamdouh13.data

import com.ahmedmamdouh13.data.entity.*
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import kotlinx.coroutines.CompletableDeferred
import okhttp3.MediaType
import okhttp3.ResponseBody

object Given {

    val title = "Android Project"
    val startDate = "13/9/2019"
    val endDate = "15/9/2019"

  var projectEntity = ProjectEntity(
      key = 0,
      title = title,
      startDate = startDate,
      endDate = endDate
  )
  var projectDomain = ProjectDomainModel(
      key = 0,
      title = title,
      startDate = startDate,
      endDate = endDate
  )

    var listHoliday: List<Holiday>
    val locationDomainGiven = "EG"
    val name1 = "Oct"

    val desc1 = "October Victory"
    val date = "6/10/2019"
    val element1 =
        HolidaysDomain(name1, date, locationDomainGiven, desc1)
    val name2 = "Mawled"
    val desc2 = "Mawled nabawy"
    val date1 = "6/11/2019"
    val element2 =
        HolidaysDomain(name2, date1, locationDomainGiven, desc2)
    val name3 = "Eid Adha"
    val desc3 = "Eid Adha Celebration"
    val date2 = "24/8/2019"
    val element3 =
        HolidaysDomain(name3, date2, locationDomainGiven, desc3)
    val listDomainModel = listOf(element1, element2, element3)
    val STATUS_SUCCESS_DOMAIN_MODEL_LIST: MyResult<List<HolidaysDomain>> =
        MyResult(listDomainModel)
    val statusFailDomainModelList: MyResult<List<HolidaysDomain>>
   get(){
       val status =
           MyResult(emptyList<HolidaysDomain>())
       status.status = Status.ERROR
       return status
   }
    val holidaysResponse =retrofit2.Response.success(Holidays())
    val holyday1 = Holiday()
    val holyday2 = Holiday()
    val holyday3 = Holiday()
    val statusFailMessage: String = "Error 400"

    val errorHolidaysResponse = retrofit2.Response.error<Holidays>(400, ResponseBody.create(
        MediaType.parse("Text"), statusFailMessage))
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
        holyday1.date.datetime = Datetime()
        holyday2.date.datetime = Datetime()
        holyday3.date.datetime = Datetime()

        holyday1.date.iso = date
        holyday2.date.iso = date1
        holyday3.date.iso = date2
        listHoliday = listOf(holyday1, holyday2, holyday3)

        holidaysResponse.body()!!.response!!.holidays = listOf(holyday1, holyday2, holyday3)
    }
}
