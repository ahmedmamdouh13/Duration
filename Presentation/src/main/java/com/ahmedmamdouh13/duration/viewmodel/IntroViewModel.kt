package com.ahmedmamdouh13.duration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.duration.model.HolidaysModel
import com.ahmedmamdouh13.duration.model.ProjectModel

class IntroViewModel(
    private val addProjectUseCase: ProjectInteractor,
    private val getHolidaysUseCase: HolidaysInteractor
) : ViewModel() {

    internal var projectLiveData: MutableLiveData<List<ProjectModel>> = MutableLiveData()
    private var holidaysLiveData: MutableLiveData<HolidaysModel> = MutableLiveData()


    suspend fun initProjects() {
        projectLiveData.postValue(addProjectUseCase.getProjects().data.map { model ->
            ProjectModel(
                model.key,
                model.title,
                model.startDate,
                model.endDate
            )
        })
    }

    suspend fun initHolidays(timeZone: String) {
        for (model in getHolidaysUseCase.getHolidaysFoundInLocation(timeZone).data)
            holidaysLiveData.postValue(
                HolidaysModel(
                    model.name,
                    model.date,
                    model.location,
                    model.desc
                )
            )
    }


}