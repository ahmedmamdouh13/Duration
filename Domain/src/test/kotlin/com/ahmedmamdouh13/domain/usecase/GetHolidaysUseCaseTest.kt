package com.ahmedmamdouh13.domain.usecase

import com.ahmedmamdouh13.domain.status.Status
import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.usecase.GetHolidaysUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetHolidaysUseCaseTest {

    @MockK
    lateinit var repository: Repository

    private lateinit var getHolidaysUseCase: GetHolidaysUseCase

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
        }returns Given.STATUS_SUCCESS_DOMAIN_MODEL_LIST
        //then
        runBlocking {

            val status = getHolidaysUseCase.getHolidaysFoundInLocation(location)
            assertEquals(status.status, Status.SUCCESS)
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

            assertEquals(localHolidays[0].name,Given.listDomainModel[0].name)

        }


    }
}