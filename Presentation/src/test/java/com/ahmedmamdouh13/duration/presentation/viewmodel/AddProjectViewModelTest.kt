package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.duration.Given.endDate
import com.ahmedmamdouh13.duration.Given.startDate
import com.ahmedmamdouh13.duration.Given.title
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.status.Status
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
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
        }returns Status.SUCCESS
        //then
        runBlocking {
            viewModel.addProject(title, startDate, endDate)
            assertEquals(useCase.addProject(title, startDate, endDate),
                Status.SUCCESS)


        }
        verify {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
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
        }returns Status.ERROR
        //then
        runBlocking {
            viewModel.addProject(title, startDate, endDate)
            assertEquals(useCase.addProject(title, startDate, endDate),
                Status.ERROR)

        }
        verify {
            runBlocking {
                useCase.addProject(title,startDate,endDate)
            }
        }

    }
}