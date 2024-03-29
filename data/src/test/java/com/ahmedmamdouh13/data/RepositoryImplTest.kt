package com.ahmedmamdouh13.data

import com.ahmedmamdouh13.data.Given.endDate
import com.ahmedmamdouh13.data.Given.projectEntity
import com.ahmedmamdouh13.data.Given.projectKey
import com.ahmedmamdouh13.data.Given.projectTitle
import com.ahmedmamdouh13.data.Given.startDate
import com.ahmedmamdouh13.data.Given.taskTag
import com.ahmedmamdouh13.data.Given.taskTitle
import com.ahmedmamdouh13.data.Given.title
import com.ahmedmamdouh13.data.entity.TaskEntity
import com.ahmedmamdouh13.data.local.ArticleDao
import com.ahmedmamdouh13.data.local.ProjectDao
import com.ahmedmamdouh13.data.local.TasksDao
import com.ahmedmamdouh13.data.network.RetrofitService
import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.model.ProjectDomainModel
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RepositoryImplTest {

    @MockK
    lateinit var taskDao: TasksDao
    @MockK
    lateinit var projDao: ProjectDao
    @MockK
    lateinit var retrofitService: RetrofitService
    @MockK
    lateinit var dao: ArticleDao

    lateinit var repository: Repository

//    @Rule
//    @JvmField
//    var instantTaskExecutorRule  = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedGetHolidayList() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        //when
        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        } returns Given.deferredHolidaysSuccess

        every {
            runBlocking {
                dao.insertAllArticles(holiday = Given.deferredHolidaysSuccess.await().body()!!.response!!.holidays)
            }
        } returns listOf(0, 1, 2, 3)

        //then
        runBlocking {

            val status = repository.getHolidayList(Given.locationDomainGiven)

            val holiday = Given.holidaysResponse.body()!!.response!!.holidays[0]

//            dao.insertAllArticles(holiday = holiday)
            println(status.status)
            println(status.message)
//            println(status.data[0].name)
            assertEquals(status.status, Status.SUCCESS)
            assertEquals(status.data[0].name, holiday.name)


        }
    }

    @Test
    fun shouldFailGetHolidayList() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        //when

        every {
            runBlocking {
                retrofitService.getCountryHolidays(Given.locationDomainGiven)
            }
        } returns Given.deferredHolidaysError
        //then
        runBlocking {
            val status = repository.getHolidayList(Given.locationDomainGiven)
            assertEquals(status.status, Status.ERROR)
        }
    }

    @Test
    fun shouldSucceedReturnHolidaysLocal() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        //when
        every {
            runBlocking {
                dao.getAllArticle()
            }
        } returns Given.listHoliday
        //then
        runBlocking {
            val holidaysLocally = repository.getHolidaysLocally()
            assertEquals(holidaysLocally[0].name, Given.listHoliday[0].name)
        }
    }

    @Test
    fun shouldSucceedInsertProjectToRoom() {
        //given
        val id = 0L
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)

        //when


        every {
            runBlocking {
                projDao.insertProject(projectEntity = projectEntity)
            }
        } returns id
        //then
        runBlocking {
            val status = repository.addProject(title, startDate, endDate)
            assertEquals(status.status, Status.SUCCESS)
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
    fun shouldReturnListProjectsLocally() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        //when
        every {
            runBlocking {
                projDao.getProjects()
            }
        } returns listOf(projectEntity, projectEntity, projectEntity)
        //then
        runBlocking {
            val result = repository.getProjectsLocally()
            assertEquals(result.data[0].title, projectEntity.title)
        }
    }

    @Test
    fun shouldAddTask() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        //when
        every {
            runBlocking {
                taskDao.insertTask(TaskEntity(projectKey, taskTitle, taskTag, projectTitle))
            }
        } returns 1L
        //then
        runBlocking {
            val result = repository.addTask(projectKey, taskTitle, taskTag, projectTitle)
            assertEquals(result.data, 1L)
        }
        verify {
            runBlocking {
                taskDao.insertTask(TaskEntity(projectKey, taskTitle, taskTag, projectTitle))
            }
        }
    }

    @Test
    fun shouldSuccessfullyGetTasks() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        val id = 0
        //when
        every {
            runBlocking {
                taskDao.getTasks(id.toString())
            }
        } returns listOf(TaskEntity(projectKey, taskTitle, taskTag, projectTitle))
        //then
        runBlocking {
            val status = repository.getTasks(id.toString()).status
            assertEquals(status, Status.SUCCESS)
        }
    }

    @Test
    fun shouldFailGetTasks() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        val id = 0
        //when
        every {
            runBlocking {
                taskDao.getTasks(id.toString())
            }
        } returns listOf(TaskEntity(projectKey, taskTitle, taskTag, projectTitle))
        //then
        runBlocking {
            repository.getTasks(id.toString())
        }
    }

    @Test
    fun shouldGetProjectByIdSuccess() {
        //given
        repository = RepositoryImpl(retrofitService, dao, projDao, taskDao)
        val id = 0
        //when
        every {
            runBlocking {
                projDao.getProjectById(id)
            }
        } returns projectEntity
        //then
        runBlocking {
            val result: MyResult<ProjectDomainModel> = repository.getProjectById(id)
            assertEquals(result.status, Status.SUCCESS)
        }


    }

}