package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.domain.usecase.GetHolidaysUseCase
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainViewModel(private var holidaysUseCase: HolidaysInteractor) : ViewModel() {
   suspend fun getHolidayListInLocation(location: String): ArticleStatus<List<HolidaysDomain>> {

       return holidaysUseCase.getHolidaysFoundInLocation(location)
    }


}
