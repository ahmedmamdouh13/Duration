package com.ahmedmamdouh13.data.network

import com.ahmedmamdouh13.data.entity.Holidays
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/api/v2/holidays?&api_key=39ac67b125b4c3463e43a5eca480fb59c178717a&year=2019")
    fun getCountryHolidays(@Query("country")country:String) : Deferred<Response<Holidays>>
}