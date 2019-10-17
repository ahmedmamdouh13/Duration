package com.ahmedmamdouh13.data

import com.ahmedmamdouh13.data.entity.ProjectEntity
import com.ahmedmamdouh13.data.entity.TaskEntity
import com.ahmedmamdouh13.data.local.ArticleDao
import com.ahmedmamdouh13.data.local.DateTypeConverter
import com.ahmedmamdouh13.data.local.ProjectDao
import com.ahmedmamdouh13.data.local.TasksDao
import com.ahmedmamdouh13.data.network.RetrofitService
import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status

class RepositoryImpl(
    private var retrofitService: RetrofitService,
    private var dao: ArticleDao,
    private var projDao: ProjectDao,
    private var taskDao: TasksDao
) : Repository{
    override suspend fun getHolidayList(s: String): MyResult<List<HolidaysDomain>> {
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

    override suspend fun addProject(title: String, startDate: String, endDate: String): MyResult<Long> {

        val projectEntity = ProjectEntity(
            title = title,
            startDate = startDate,
            endDate = endDate
        )
        return  try {

            val isCompleted = projDao.insertProject(projectEntity)
            println(isCompleted)
              MyResult(isCompleted).apply {
                status = Status.SUCCESS
                message = "Project : ($title) is successfully saved."
            }
        }catch (e:Exception){
            MyResult(-1L).apply {
                status = Status.ERROR
                 e.message?.let {
                     message = it
                }
            }
        }
    }

    override suspend fun getProjectsLocally(): MyResult<List<ProjectDomainModel>> {
    return try {

        MyResult(data = projDao.getProjects().map {
            ProjectDomainModel(
                it.key,
                it.title,
                it.startDate,
                it.endDate
            )
        })
            .also {
                it.status = Status.SUCCESS
            }
    }catch (e:Exception){
        MyResult(data =
            listOf<ProjectDomainModel>()
        ).apply {
            status = Status.ERROR
            e.message?.let {
             message = it
            }
            }
    }
    }

    override suspend fun getHolidaysLocally(): List<HolidaysDomain> {
    return dao.getAllArticle().map {
        it.let {

            HolidaysDomain(it.name
                , DateTypeConverter()
                    .toString(it.date)
                ,it.locations
                ,it.description ?: "")
        }
    }
    }

    override suspend fun addTask(
        projectKey: Int,
        taskTitle: String,
        taskTag: String
    ): MyResult<Long> {

       return try {

           MyResult(taskDao.insertTask(TaskEntity(projectKey,taskTitle,taskTag))).apply {
               status = Status.SUCCESS
               message = "task : $taskTitle added successfully."
           }
       }catch (e:Exception){
           MyResult(-1L).apply {
               status = Status.ERROR
           e.message?.let {
               message = it
           }
           }
       }



    }

    }


