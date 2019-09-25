package com.ahmedmamdouh13.duration.domain.usecase

import com.ahmedmamdouh13.duration.Given
import com.ahmedmamdouh13.duration.data.entity.status.Status
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class GetHolidaysUseCaseTest {

    @MockK
    lateinit var repository: ArticleRepository

    private lateinit var getHolidaysUseCase:GetHolidaysUseCase

    @Before
    fun setup(){
        MockKAnnotations.init(this,relaxUnitFun = true)
    }

    @Test
    fun getHolidays() {
    }

    @Test
    fun shouldSucceedgetHolidaysFoundInLocation() {
        //given
        val location = Given.locationDomainGiven

        getHolidaysUseCase = GetHolidaysUseCase(repository)
        //when
        every {
            runBlocking {
                repository.getHolidayList(location)
            }
        }returns Given.statusSuccessDomainModelList
        //then
        runBlocking {

            val status = getHolidaysUseCase.getHolidaysFoundInLocation(location)
            assertEquals(status.status,Status.SUCCESS)
            assertEquals(status.data[0].name,Given.listDomainModel[0].name)
        }


    }

    @Test
    fun shouldSucceedReturnHolidaysLocal(){
        //given
        getHolidaysUseCase = GetHolidaysUseCase(repository)
        //when
        every {
        runBlocking {
            repository.getHolidaysLocally()
            }
        }returns Given.listDomainModel
        //then
        runBlocking {
            val localHolidays = getHolidaysUseCase.getLocalHolidays()

            assertEquals(localHolidays[0].name,Given.listHoliday[0].name)

        }


    }
}