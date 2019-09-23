package com.ahmedmamdouh13.duration.presentation.viewmodel

import io.mockk.*
import org.junit.Before
import org.junit.Rule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.Given.listDomainModel
import com.ahmedmamdouh13.duration.Given.locationDomainGiven
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

//@RunWith(RobolectricTestRunner::class)
//@Config(constants = BuildConfig::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var viewStateObserver: Observer<String>
    @MockK
    lateinit var holidaysUseCase: HolidaysInteractor

    private lateinit var mainViewModel : MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }


    @Test
    fun shouldReturnPOJOHolidays(){
        //given
        mainViewModel = MainViewModel(holidaysUseCase)

        //when
        every {
            runBlocking {
                holidaysUseCase.getHolidaysFoundInLocation(locationDomainGiven)
            }
        }returns Given.statusDomainModelList
        runBlocking {

            val status = mainViewModel.getHolidayListInLocation(locationDomainGiven)
            //then
            assert(status.status == Status.SUCCESS)
            assert(status.data[0].location == locationDomainGiven)
            assert(status.data[1].location == locationDomainGiven)
            assert(status.data[2].location == locationDomainGiven)
        }


    }


}