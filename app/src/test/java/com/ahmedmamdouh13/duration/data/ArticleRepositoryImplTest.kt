package com.ahmedmamdouh13.duration.data

import androidx.room.Room
import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.data.local.ArticleDao
import com.ahmedmamdouh13.duration.data.local.ArticleDatabase
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.robolectric.Robolectric
import kotlin.coroutines.coroutineContext

class ArticleRepositoryImplTest {

    @MockK
    lateinit var retrofitService: RetrofitService
    @MockK
    lateinit var dao: ArticleDao

    lateinit var repository : ArticleRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)

    }

    @Test
    fun shouldSucceedGetHolidayList() {
        //given
        repository = ArticleRepositoryImpl(retrofitService,dao)
        //when
        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        }returns Given.deferredHolidaysSuccess

        every {
            runBlocking {
                dao.insertAllArticles(holiday = Given.deferredHolidaysSuccess.await().body()!!.response!!.holidays!!)
            }
        }returns listOf(0,1,2,3)

        //then
        runBlocking {

            val status = repository.getHolidayList(Given.locationDomainGiven)

            val holiday = Given.holidaysResponse.body()!!.response!!.holidays!![0]

//            dao.insertAllArticles(holiday = holiday)
            println(status.status)
            println(status.message)
//            println(status.data[0].name)
            assertEquals(status.status,Status.SUCCESS)
            assertEquals(status.data[0].name, holiday.name)


        }
    }
    @Test
    fun shouldFailGetHolidayList() {
        //given
        repository = ArticleRepositoryImpl(retrofitService, dao)
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
    @Test
    fun shouldSucceedReturnHolidaysLocal(){
        //given
        repository = ArticleRepositoryImpl(retrofitService,dao)
        //when
        every {
            runBlocking {
                dao.getAllArticle()
            }
        }returns Given.listHoliday
        //then
        runBlocking {
            val holidaysLocally = repository.getHolidaysLocally()
            assertEquals(holidaysLocally[0].name ,Given.listHoliday[0].name)
        }
    }

}