package com.ahmedmamdouh13.duration.data

import com.ahmedmamdouh13.duration.data.entity.Holiday
import com.ahmedmamdouh13.duration.data.entity.Holidays
import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import kotlinx.coroutines.*
import retrofit2.HttpException

class ArticleRepositoryImpl(private var retrofitService: RetrofitService) : ArticleRepository{
    override suspend fun getHolidayList(s: String): ArticleStatus<List<HolidaysDomain>>{
        val response = retrofitService.getCountryHolidays(s)

        val await = response.await()
        return if (await.isSuccessful) {
            val list = await.body()!!
                .response!!.holidays!!
                .map {

                    HolidaysDomain(it.name!!, it.date.toString(), it.locations!!, " ")

                }
            ArticleStatus(list)
        }
        else {
            val articleStatus = ArticleStatus(listOf<HolidaysDomain>())
            articleStatus.status = Status.ERROR
            articleStatus
        }
    }

    }


