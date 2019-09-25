package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.duration.data.entity.status.ArticleStatus
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.domain.usecase.GetHolidaysUseCase
import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel
import com.ahmedmamdouh13.duration.presentation.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MainViewModel(private var holidaysUseCase: HolidaysInteractor) : ViewModel() {

    private lateinit var view: MainView

    suspend fun getHolidayListInLocation(location: String) {

       val status = holidaysUseCase.getHolidaysFoundInLocation(location)
       if (status.status == Status.SUCCESS) {
           val list = status.data.map {
               HolidaysModel(it.name, it.date, it.location, it.desc)
           }
           view.displayList(list)
       }else if (status.status == Status.ERROR) {
           println(status.message)
           GlobalScope.launch(Dispatchers.Main) {
               view.toast(status.message)
           }
           val localHolidays = holidaysUseCase.getLocalHolidays()
           println(localHolidays[0].name)
           println(localHolidays[0].date)
           println(localHolidays[0].location)


       }
    }

    fun bind(mainView: MainView) {
        view=mainView
    }


}
