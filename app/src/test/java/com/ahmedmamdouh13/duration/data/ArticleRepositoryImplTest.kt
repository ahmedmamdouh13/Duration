package com.ahmedmamdouh13.duration.data

import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ArticleRepositoryImplTest {

    @MockK
    lateinit var retrofitService: RetrofitService

    lateinit var repository : ArticleRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedGetHolidayList() {
        //given
        repository = ArticleRepositoryImpl(retrofitService)
        //when
        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        }returns Given.deferredHolidaysSuccess

        //then
        runBlocking {

            val status = repository.getHolidayList(Given.locationDomainGiven)
            assertEquals(status.status,Status.SUCCESS)
            assertEquals(status.data[0].name, Given.holidaysResponse.body()!!.response!!.holidays!![0].name)


        }
    }
    @Test
    fun shouldFailGetHolidayList() {
        //given
        repository = ArticleRepositoryImpl(retrofitService)
        //when
        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        }returns Given.deferredHolidaysError
        //then
        runBlocking {
            val status= repository.getHolidayList(Given.locationDomainGiven)
                assertEquals(status.status,Status.ERROR)
        }
    }

}