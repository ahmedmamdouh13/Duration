package com.ahmedmamdouh13.duration.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddTaskViewModelTest {
    @MockK
    lateinit var useCase: ProjectInteractor

    lateinit var viewModel: AddTaskViewModel

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }


    @Test
    fun `should add project to database`() {


    }

    @Test
    fun `should return project from database`() {

    }

    @Test
    fun `should add task to database`() {

    }

    @Test
    fun `should return task from database`() {

    }

    @Test
    fun `should fail add task to database`() {

    }

    @Test
    fun `should fail retrun task from database`() {

    }

}