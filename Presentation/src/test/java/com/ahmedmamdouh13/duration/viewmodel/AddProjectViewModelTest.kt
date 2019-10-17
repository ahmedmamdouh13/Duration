package com.ahmedmamdouh13.duration.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.duration.Given.endDate
import com.ahmedmamdouh13.duration.Given.projectKey
import com.ahmedmamdouh13.duration.Given.startDate
import com.ahmedmamdouh13.duration.Given.taskTag
import com.ahmedmamdouh13.duration.Given.taskTitle
import com.ahmedmamdouh13.duration.Given.title
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddProjectViewModelTest {

   lateinit var viewModel:AddProjectViewModel
    @MockK
    lateinit var useCase: ProjectInteractor

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedAddProjectToDB(){
        //given
        viewModel = AddProjectViewModel(useCase)
        val title = "Android Project"
        val startDate = "13/9/2019"
        val endDate = "15/9/2019"
        //when
        every {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
        }returns MyResult(1L).apply { status = Status.SUCCESS}
        //then
        runBlocking {
            viewModel.addProject(title, startDate, endDate)
            assertEquals(useCase.addProject(title, startDate, endDate).status,
                Status.SUCCESS)


        }
        verify {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
        }

    }

    @Test
    fun refactorShouldReturnAddProjectToDb(){
        //given
        viewModel = AddProjectViewModel(useCase)
        //when
        every {
            runBlocking {
                useCase.addProject(title, startDate, endDate)
            }
        }returns MyResult(1L).apply { status = Status.SUCCESS }

        //then
        runBlocking {
            viewModel.addProject(title, startDate, endDate)

           val id =  useCase.addProject(title, startDate, endDate).data
            assertEquals(id,1)
        }

    }

    @Test
    fun shouldFailAddProjectToDB(){
        //given
        viewModel = AddProjectViewModel(useCase)

        //when
        every {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
        }returns MyResult(1L).apply { status = Status.ERROR}
        //then
        runBlocking {
            viewModel.addProject(title, startDate, endDate)
            assertEquals(useCase.addProject(title, startDate, endDate).status,
                Status.ERROR)

        }
        verify {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
        }

    }

    @Test
    fun shouldSucceedAddTask(){
        //given
        viewModel = AddProjectViewModel(useCase)
        //when
        every {
            runBlocking {
                useCase.addTask(projectKey,taskTitle,taskTag)
            }
        }returns MyResult(1L).apply {status = Status.SUCCESS}
        //then
        runBlocking {
            viewModel.addTask(key = projectKey,title =  taskTitle,tag = taskTag)
            verify {
                runBlocking {
                    useCase.addTask(projectKey,taskTitle, taskTag)
                }
            }
            val result = useCase.addTask(projectKey, taskTitle, taskTag)
            assertEquals(result.status ,Status.SUCCESS)
        }
    }

    @Test
    fun shouldFailAddTask(){
        //given
        viewModel = AddProjectViewModel(useCase)
        //when
        every {
            runBlocking {
                useCase.addTask(projectKey,taskTitle,taskTag)
            }
        }returns MyResult(1L).apply {status = Status.ERROR}
        //then
        runBlocking {
            viewModel.addTask(key = projectKey,title =  taskTitle,tag = taskTag)
            verify {
                runBlocking {
                    useCase.addTask(projectKey,taskTitle, taskTag)
                }
            }
            val result = useCase.addTask(projectKey, taskTitle, taskTag)
            assertEquals(result.status ,Status.ERROR)
        }
    }

}