package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.duration.Given.STATUS_SUCCESS_DOMAIN_MODEL_LIST
import com.ahmedmamdouh13.duration.Given.locationDomainGiven
import com.ahmedmamdouh13.duration.Given.projectDomain
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.status.MyResult
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

class IntroViewModelTest {


    lateinit var introViewModel: IntroViewModel

    @MockK
    lateinit var projectsUseCase : ProjectInteractor
    @MockK
    lateinit var holidaysUseCase: HolidaysInteractor

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedReturnProjectsLocally(){

        //given
        introViewModel = IntroViewModel(projectsUseCase, holidaysUseCase)
        val myResult = MyResult(
            listOf(
                projectDomain,
                projectDomain,
                projectDomain
            )
        )
        //when
        every {
            runBlocking {
                projectsUseCase.getProjects()
            }
        }returns myResult
        //then
        runBlocking {
            introViewModel.initProjects()

        }
        verify {
            runBlocking {
                projectsUseCase.getProjects()
            }
        }

        runBlocking {
            assertEquals(projectsUseCase.getProjects().data,myResult.data)
        }

    }

    @Test
    fun shouldSucceedReturnHolidaysNetwork(){
        //given
        introViewModel = IntroViewModel(projectsUseCase,holidaysUseCase)
        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns STATUS_SUCCESS_DOMAIN_MODEL_LIST
        //then
        runBlocking {
            introViewModel.initHolidays(locationDomainGiven)
        }
        verify {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }
        runBlocking {
            val result =
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            assertEquals(result.status, Status.SUCCESS)
            assertEquals(result.data[0].name, STATUS_SUCCESS_DOMAIN_MODEL_LIST.data[0].name)
        }
    }
}