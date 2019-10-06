package com.ahmedmamdouh13.duration.data

import com.ahmedmamdouh13.duration.data.entity.ProjectEntity
import com.ahmedmamdouh13.duration.data.local.ArticleDao
import com.ahmedmamdouh13.duration.data.local.DateTypeConverter
import com.ahmedmamdouh13.duration.data.local.ProjectDao
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.Repository
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.status.Status

class RepositoryImpl(
    private var retrofitService: RetrofitService,
    private var dao: ArticleDao,
    private var projDao: ProjectDao
) : Repository{
    override suspend fun getHolidayList(s: String): MyResult<List<HolidaysDomain>>{
        val response = retrofitService.getCountryHolidays(s)
      return  try {
            val await = response.await()
             if (await.isSuccessful) {
                 val holidays = await.body()?.response?.holidays
                 val list = holidays!!.map {

                     HolidaysDomain(it.name, DateTypeConverter().run{toString(it.date)}, it.locations, it.description ?: "" )

                 }
                 val articleStatus = MyResult(list)
                 articleStatus.status = Status.SUCCESS
                 articleStatus.message = "Holidays loaded successfully."



                  holidays.let {mList ->

                          val insertArticle = mList.let { dao.insertAllArticles(it) }

                      println(list[0].name + "Impl ${insertArticle[0]}")
                      println(list[1].name + "Impl ${insertArticle[1]}")
                  }


                 println(articleStatus.status )
                 articleStatus
        }
        else {
                 val exceptionOrNull = await.message()
             throw Exception(exceptionOrNull)
        }


        }
        catch (e : Exception){
            val articleStatus = MyResult(listOf<HolidaysDomain>())
            articleStatus.status = Status.ERROR
            e.message?.let {
                articleStatus.message = it
            }
            articleStatus
        }
    }

    override suspend fun addProject(title: String, startDate: String, endDate: String): Status {

        val projectEntity = ProjectEntity(title = title, startDate = startDate, endDate = endDate)
        val isCompleted = projDao.insertProject(projectEntity)
        println(isCompleted)
        return  Status.SUCCESS
    }

    override suspend fun getProjectsLocally(): MyResult<List<ProjectDomainModel>> {
    return MyResult(data = projDao.getProjects().map { ProjectDomainModel(it.key,it.title,it.startDate,it.endDate) })
        .also {
            it.status = Status.SUCCESS
        }
    }

    override suspend fun getHolidaysLocally(): List<HolidaysDomain> {
    return dao.getAllArticle().map {
        it.let {

            HolidaysDomain(it.name
                ,DateTypeConverter()
                    .toString(it.date)
                ,it.locations
                ,it.description ?: "")
        }
    }
    }

    }


