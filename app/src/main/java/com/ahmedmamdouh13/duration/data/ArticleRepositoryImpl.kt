package com.ahmedmamdouh13.duration.data

import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.data.local.ArticleDao
import com.ahmedmamdouh13.duration.data.local.DateTypeConverter
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.suspendCoroutine

class ArticleRepositoryImpl(
    private var retrofitService: RetrofitService,
    private var dao: ArticleDao ) : ArticleRepository{
    override suspend fun getHolidayList(s: String): ArticleStatus<List<HolidaysDomain>>{
        val response = retrofitService.getCountryHolidays(s)
      return  try {
            val await = response.await()
             if (await.isSuccessful) {
                 val holidays = await.body()?.response?.holidays
                 val list = holidays!!.map {

                     HolidaysDomain(it.name, DateTypeConverter().run{toString(it.date)}, it.locations, it.description ?: "" )

                 }
                 val articleStatus = ArticleStatus(list)
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
            val articleStatus = ArticleStatus(listOf<HolidaysDomain>())
            articleStatus.status = Status.ERROR
            e.message?.let {
                articleStatus.message = it
            }
            articleStatus
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


