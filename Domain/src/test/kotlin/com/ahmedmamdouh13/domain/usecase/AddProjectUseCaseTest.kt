package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.status.MyResult
import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.domain.usecase.Given.endDate
import com.ahmedmamdouh13.domain.usecase.Given.projectDomain
import com.ahmedmamdouh13.domain.usecase.Given.projectKey
import com.ahmedmamdouh13.domain.usecase.Given.projectTitle
import com.ahmedmamdouh13.domain.usecase.Given.startDate
import com.ahmedmamdouh13.domain.usecase.Given.taskDomainList
import com.ahmedmamdouh13.domain.usecase.Given.taskTag
import com.ahmedmamdouh13.domain.usecase.Given.taskTitle
import com.ahmedmamdouh13.domain.usecase.Given.title
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddProjectUseCaseTest {

    @MockK
    internal lateinit var repo: Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun shouldSucceedAddProject() {
        //given
        val useCase = AddProjectUseCase(repo)

        //when
        every {
            runBlocking {
                repo.addProject(title, startDate, endDate)
            }
        } returns MyResult(1L).apply { status = Status.SUCCESS }
        //then
        runBlocking {
            val status = useCase.addProject(title, startDate, endDate)
            assert(status.status == Status.SUCCESS)
        }
        verify {
            runBlocking {

                repo.addProject(title, startDate, endDate)
            }
        }

    }

    @Test
    fun shouldReturnResultListProjects() {
        //given
        val useCase = AddProjectUseCase(repo)
        //when
        every {
            runBlocking {
                repo.getProjectsLocally()
            }
        } returns MyResult(
            listOf(
                projectDomain,
                projectDomain,
                projectDomain
            )
        )
        //then
        runBlocking {
            val myResult = useCase.getProjects()
            assert(myResult.data[0].title == projectDomain.title)
        }
        verify {
            runBlocking {
                repo.getProjectsLocally()
            }
        }

    }

    @Test
    fun shouldAddTaskToRepo() {
        //given
        val useCase = AddProjectUseCase(repo)
        //when
        every {
            runBlocking {
                repo.addTask(projectKey, taskTitle, taskTag, projectTitle)
            }
        } returns MyResult<Long>(1).apply { status = Status.SUCCESS }
        //then
        runBlocking {
            val result = useCase.addTask(projectKey, taskTitle, taskTag, projectTitle)
            assert(result.status == Status.SUCCESS)
        }
        verify {
            runBlocking {
                repo.addTask(projectKey, taskTitle, taskTag, projectTitle)
            }
        }
    }

    @Test
    fun shouldSucceedGetAllTasks() {
        //given
        val useCase = AddProjectUseCase(repo)
        val id = 0
        //when
        every {
            runBlocking {
                repo.getTasks(id.toString())
            }
        } returns MyResult(taskDomainList).apply { status = Status.SUCCESS }
        //then
        runBlocking {
            val result = useCase.getTasks(id)
            assert(result.status == Status.SUCCESS)
        }
        verify {
            runBlocking {
                repo.getTasks(id.toString())
            }
        }
    }

    @Test
    fun shouldSucceedGetProjectById() {
        //given
        val useCase = AddProjectUseCase(repo)
        val id = 0
        //when
        every {
            runBlocking {
                repo.getProjectById(id)
            }
        } returns MyResult(projectDomain).apply { status = Status.SUCCESS }
        //then
        runBlocking {
            val result = useCase.getProjectById(id)
            assert(result.status == Status.SUCCESS)
        }
        verify {
            runBlocking {
                repo.getProjectById(id)
            }
        }
    }

}