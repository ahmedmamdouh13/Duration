package com.ahmedmamdouh13.duration.domain

import com.ahmedmamdouh13.duration.data.entity.Holidays
import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import kotlinx.coroutines.Deferred

interface ArticleRepository {
   suspend fun getHolidayList(s: String): ArticleStatus<List<HolidaysDomain>>

}
