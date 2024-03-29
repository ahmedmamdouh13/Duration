package com.ahmedmamdouh13.duration.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.duration.model.TaskModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddProjectViewModel(private val projectUseCase: ProjectInteractor) : ViewModel() {
    internal val title: MutableLiveData<String> = MutableLiveData()
    internal val deadLine: MutableLiveData<String> = MutableLiveData()
    internal val updateUISuccess: MutableLiveData<MyResult<Long>> = MutableLiveData()
    internal val updateUIFailed: MutableLiveData<String> = MutableLiveData()
    internal val taskList: MutableLiveData<List<TaskModel>> = MutableLiveData()


    suspend fun addProject(title: String, startDate: String, endDate: String) {
        viewModelScope.launch(Dispatchers.IO) {

            val result = projectUseCase.addProject(title, startDate, endDate)
            when (result.status) {

                Status.SUCCESS -> updateUISuccess.postValue(result)
                Status.ERROR -> updateUIFailed.postValue("Error Adding Project , Please Try Again !")
            }
        }
        println("geeeeeeeeeeeeeeet $title")

    }

    suspend fun addTask(
        title: String,
        tag: String,
        key: Int,
        projectTitle: String
    ) {
        projectUseCase.addTask(key, title, tag, projectTitle).run {
            when (status) {
                Status.SUCCESS -> updateUISuccess.postValue(this)
                else -> updateUIFailed.postValue(message)
            }
        }
    }

    suspend fun getListObserver(id: Int): MutableLiveData<List<TaskModel>> {
        val result = projectUseCase.getTasks(id)
        when (result.status) {
            Status.SUCCESS -> taskList.postValue(result.data.map {
                TaskModel(
                    it.key,
                    it.title,
                    it.tag
                )
            })
            else -> updateUIFailed.postValue(result.message)
        }
        return taskList
    }

    suspend fun getProjectLocally(id: Int) {
        val result = projectUseCase.getProjectById(id)
        if (result.status == Status.SUCCESS) {
            title.postValue(result.data.title)
            deadLine.postValue(result.data.endDate)
        } else {
            updateUIFailed.postValue("Sorry problem !")

        }
    }


}