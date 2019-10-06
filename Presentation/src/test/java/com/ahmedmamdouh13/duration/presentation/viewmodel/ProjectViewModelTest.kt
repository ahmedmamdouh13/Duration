package com.ahmedmamdouh13.duration.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.duration.Given.STATUS_SUCCESS_DOMAIN_MODEL_LIST
import com.ahmedmamdouh13.duration.Given.locationDomainGiven
import com.ahmedmamdouh13.duration.Given.statusFailDomainModelList
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.domain.usecase.GetHolidaysUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test


class ProjectViewModelTest {

    lateinit var projectViewModel: ProjectViewModel

    @MockK
    lateinit var holidaysUseCase: GetHolidaysUseCase

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedReturnHolidaysRemotely(){
        //given
        projectViewModel = ProjectViewModel(holidaysUseCase)
        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns STATUS_SUCCESS_DOMAIN_MODEL_LIST
        //then
        runBlocking {
            projectViewModel.initHolidays(locationDomainGiven)
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
    @Test
    fun shouldFailReturnHolidaysRemotely(){
        //given
        projectViewModel = ProjectViewModel(holidaysUseCase)
        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns statusFailDomainModelList
        //then
        runBlocking {
            projectViewModel.initHolidays(locationDomainGiven)
        }
        verify {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }
            runBlocking {
                val result =
                    holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
               assertEquals(result.status, Status.ERROR)
               assertEquals(result.message, statusFailDomainModelList.message)
            }
    }
}