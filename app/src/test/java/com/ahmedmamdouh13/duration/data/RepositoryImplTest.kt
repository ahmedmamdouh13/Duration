package com.ahmedmamdouh13.duration.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.Given.endDate
import com.ahmedmamdouh13.duration.Given.projectEntity
import com.ahmedmamdouh13.duration.Given.startDate
import com.ahmedmamdouh13.duration.Given.title
import com.ahmedmamdouh13.duration.data.entity.ProjectEntity
import com.ahmedmamdouh13.duration.domain.status.Status
import com.ahmedmamdouh13.duration.data.local.ArticleDao
import com.ahmedmamdouh13.duration.data.local.ProjectDao
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.Repository
import com.ahmedmamdouh13.duration.domain.model.ProjectDomainModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockkClass
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule

class RepositoryImplTest {

    @MockK
    lateinit var projDao: ProjectDao
    @MockK
    lateinit var retrofitService: RetrofitService
    @MockK
    lateinit var dao: ArticleDao

    lateinit var repository : Repository

    @Rule
    @JvmField
    var instantTaskExecutorRule  = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedGetHolidayList() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao)
        //when
        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        }returns Given.deferredHolidaysSuccess

        every {
            runBlocking {
                dao.insertAllArticles(holiday = Given.deferredHolidaysSuccess.await().body()!!.response!!.holidays)
            }
        }returns listOf(0,1,2,3)

        //then
        runBlocking {

            val status = repository.getHolidayList(Given.locationDomainGiven)

            val holiday = Given.holidaysResponse.body()!!.response!!.holidays[0]

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
        repository = RepositoryImpl(retrofitService, dao, projDao)
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
        repository = RepositoryImpl(retrofitService, dao, projDao)
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

    @Test
    fun shouldSucceedInsertProjectToRoom(){
        //given
       val id =  0L
        repository = RepositoryImpl(retrofitService,dao,projDao)

        //when


        every {
            runBlocking {
                projDao.insertProject(projectEntity = projectEntity)
            }
        }returns id
        //then
        runBlocking {
           val status = repository.addProject(title, startDate, endDate)
            assertEquals(status,Status.SUCCESS)
        }
      verify {
           runBlocking {
               projDao.insertProject(projectEntity)
            }
        }

    }

//    @Test
//    fun shouldFailInsertProjectToRoom(){
//        //given
//       val id =  "-1".toLong()
//        repository = RepositoryImpl(retrofitService,dao,projDao)
//
//        //when
//
//
//        every {
//            runBlocking {
//                projDao.insertProject(projectEntity = projectEntity)
//            }
//        }returns id
//        //then
//        runBlocking {
//           val status = repository.addProject(title, startDate, endDate)
//            assertEquals(status,Status.ERROR)
//        }
//      verify {
//           runBlocking {
//               projDao.insertProject(projectEntity)
//            }
//        }
//
//    }
    @Test
    fun shouldReturnListProjectsLocally(){
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao)
        //when
        every {
            runBlocking {
                projDao.getProjects()
            }
        }returns listOf(projectEntity, projectEntity, projectEntity)
        //then
        runBlocking {
            val result= repository.getProjectsLocally()
            assertEquals(result.data[0].title, projectEntity.title)
        }
    }

}