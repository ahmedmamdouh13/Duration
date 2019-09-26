package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel

class MainViewModel(private var holidaysUseCase: HolidaysInteractor) : ViewModel() {

   internal var msg : MutableLiveData<String> = MutableLiveData()
   internal var viewList : MutableLiveData<List<HolidaysModel>> = MutableLiveData()

    suspend fun getHolidayListInLocation(location: String) {

       val status = holidaysUseCase.getHolidaysFoundInLocation(location)
        when {
            status.status == Status.SUCCESS ->

                status.data.map {
                HolidaysModel(it.name, it.date, it.location, it.desc)
            }.apply {
                viewList.postValue(this)
            }

            status.status == Status.ERROR ->
            {
                println(status.message)

                holidaysUseCase.getLocalHolidays()
                    .map {
                        HolidaysModel(it.name, it.date, it.location, it.desc)
                    }
                    .run {
                        viewList.postValue(this)
                    }


            }
        }
        msg.postValue(status.message)
    }



}
