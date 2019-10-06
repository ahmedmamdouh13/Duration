package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel

class ProjectViewModel(private val holidaysUseCase: HolidaysInteractor) : ViewModel() {
    internal val holidayLiveData:MutableLiveData<List<HolidaysModel>> = MutableLiveData()
    internal val displayMsgLiveData:MutableLiveData<String> = MutableLiveData()


    suspend fun initHolidays(location: String) {
        val result = holidaysUseCase.getHolidaysFoundInLocation(location)
        if (result.status == Status.SUCCESS) {
            holidayLiveData.postValue(result.data.map {
                HolidaysModel(it.name,it.date,it.location,it.desc)
            })
            displayMsgLiveData.postValue(result.message)
        }else{
            displayMsgLiveData.postValue(result.message)
            println(result.message)
        }
    }


}