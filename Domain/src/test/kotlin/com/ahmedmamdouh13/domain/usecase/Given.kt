package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.model.TaskDomain
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status

object Given {

    val projectKey: Int = 1
    val taskTag = "tag"
    val taskTitle = "title"
    val title = "Android Project"
    val startDate = "13/9/2019"
    val endDate = "15/9/2019"
    val taskDomainList = listOf(TaskDomain(projectKey, taskTitle, taskTag))


  var projectDomain = ProjectDomainModel(
      key = 0,
      title = title,
      startDate = startDate,
      endDate = endDate
  )

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



}
