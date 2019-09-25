package com.ahmedmamdouh13.duration.presentation.viewmodel

import io.mockk.*
import org.junit.Before
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.Given.locationDomainGiven
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.model.HolidaysDomain
import com.ahmedmamdouh13.duration.presentation.model.HolidaysModel
import com.ahmedmamdouh13.duration.presentation.view.MainView

import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.setMain
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var viewStateObserver: Observer<String>
    @MockK
    lateinit var holidaysUseCase: HolidaysInteractor

    @MockK
    lateinit var mainView: MainView

    private lateinit var mainViewModel : MainViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        Dispatchers.setMain(Dispatchers.Default)
        MockKAnnotations.init(this,relaxUnitFun = true)

    }


    @Test
    fun shouldSucceedReturnPOJOHolidays(){
        //given

        mainViewModel = MainViewModel(holidaysUseCase)

        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns Given.statusSuccessDomainModelList
        runBlocking {

            mainViewModel.getHolidayListInLocation(locationDomainGiven)

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
        mainViewModel = MainViewModel(holidaysUseCase)
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
            mainViewModel.getHolidayListInLocation(locationDomainGiven)
            val status = holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            //then
            assert(status.status == Status.ERROR)
            assert(status.data == emptyList<HolidaysDomain>())
        }


    }
    @Test
    fun shouldSucceedGetHolidaysLocal(){
        //given
        mainViewModel = MainViewModel(holidaysUseCase)
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
            mainViewModel.getHolidayListInLocation(locationDomainGiven)
            val localHolidays = holidaysUseCase.getLocalHolidays()
            println(localHolidays[0].name)
            println(localHolidays[1].name)
        }


    }


}