package com.ahmedmamdouh13.duration.domain.usecase

import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.Given.endDate
import com.ahmedmamdouh13.duration.Given.projectDomain
import com.ahmedmamdouh13.duration.Given.projectEntity
import com.ahmedmamdouh13.duration.Given.startDate
import com.ahmedmamdouh13.duration.Given.title
import com.ahmedmamdouh13.duration.data.entity.ProjectEntity
import com.ahmedmamdouh13.duration.domain.Repository
import com.ahmedmamdouh13.duration.domain.status.MyResult
import com.ahmedmamdouh13.duration.domain.status.Status
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class AddProjectUseCaseTest {

    @MockK
    internal lateinit var repo:Repository

    @Before
    fun setUp() {
        MockKAnnotations.init(this,relaxUnitFun = true)
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
        } returns Status.SUCCESS
        //then
        runBlocking {
            val status = useCase.addProject(title, startDate, endDate)
            assert(status == Status.SUCCESS)
        }
        verify {
            runBlocking {

                repo.addProject(title, startDate, endDate)
            }
        }

    }
    @Test
    fun shouldReturnResultListProjects(){
        //given
        val useCase = AddProjectUseCase(repo)
        //when
        every {
            runBlocking {
                repo.getProjectsLocally()
            }
        }returns MyResult(listOf(projectDomain, projectDomain, projectDomain))
        //then
        runBlocking {
            val myResult = useCase.getProjects()
           assert(myResult.data[0].title == projectEntity.title)
        }
        verify {
            runBlocking {
                repo.getProjectsLocally()
            }
        }

    }
}