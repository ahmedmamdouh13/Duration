package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddProjectViewModel(private val projectUseCase: ProjectInteractor) : ViewModel() {
    internal val updateUISuccess: MutableLiveData<String> = MutableLiveData()
    internal val updateUIFailed: MutableLiveData<String> = MutableLiveData()

    suspend fun addProject(title: String, startDate: String, endDate: String) {
        withContext(Dispatchers.IO){
            when(projectUseCase.addProject(title, startDate, endDate)){
                Status.SUCCESS -> updateUISuccess.postValue("$title Project Added Successfully !")
                Status.ERROR -> updateUIFailed.postValue("Error Adding Project , Please Try Again !")
            }
        }
    }


}