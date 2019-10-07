package com.ahmedmamdouh13.duration.viewmodel

import io.mockk.*
import org.junit.Before
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.Given.locationDomainGiven
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.model.HolidaysModel

import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Test

class HolidaysViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var viewStateObserver: Observer<String>
    @MockK
    lateinit var holidaysUseCase: HolidaysInteractor


    private lateinit var holidaysViewModel : HolidaysViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        Dispatchers.setMain(Dispatchers.Default)
        MockKAnnotations.init(this,relaxUnitFun = true)

    }


    @Test
    fun shouldSucceedReturnPOJOHolidays(){
        //given

        holidaysViewModel = HolidaysViewModel(holidaysUseCase)

        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns Given.STATUS_SUCCESS_DOMAIN_MODEL_LIST
        runBlocking {

            holidaysViewModel.getHolidayListInLocation(locationDomainGiven)

            //then
            val status =
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)

            assert(status.status == Status.SUCCESS)
            assert(status.data[0].location == locationDomainGiven)
            assert(status.data[1].location == locationDomainGiven)
            assert(status.data[2].location == locationDomainGiven)

            status.data.map {
                HolidaysModel(it.name,it.date,it.location,it.desc)
            }
        }


    }
    @Test
    fun shouldFailReturnPOJOHolidays(){
        //given
        holidaysViewModel = HolidaysViewModel(holidaysUseCase)
        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns Given.statusFailDomainModelList

        every {
            runBlocking {

                holidaysUseCase.getLocalHolidays()
            }
        }returns Given.listDomainModel

        runBlocking {
            holidaysViewModel.getHolidayListInLocation(locationDomainGiven)
            val status = holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            //then
            assert(status.status == Status.ERROR)
            assert(status.data == emptyList<HolidaysDomain>())
        }


    }
    @Test
    fun shouldSucceedGetHolidaysLocal(){
        //given
        holidaysViewModel = HolidaysViewModel(holidaysUseCase)
        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns Given.statusFailDomainModelList

        every {
            runBlocking {
                holidaysUseCase.getLocalHolidays()
            }
        }returns Given.listDomainModel
        //then
        runBlocking {
            holidaysViewModel.getHolidayListInLocation(locationDomainGiven)
            val localHolidays = holidaysUseCase.getLocalHolidays()
            println(localHolidays[0].name)
            println(localHolidays[1].name)
        }


    }


}