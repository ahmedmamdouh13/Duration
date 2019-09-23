package com.ahmedmamdouh13.duration.presentation.di

import com.ahmedmamdouh13.duration.data.ArticleRepositoryImpl
import com.ahmedmamdouh13.duration.data.network.RetrofitService
import com.ahmedmamdouh13.duration.domain.ArticleRepository
import com.ahmedmamdouh13.duration.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.duration.domain.usecase.GetHolidaysUseCase
import com.ahmedmamdouh13.duration.presentation.viewmodel.MainViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val module = module {
    single { Retrofit.Builder()
        .baseUrl("https://calendarific.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .build().create(RetrofitService::class.java)
    }
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    single<HolidaysInteractor> { GetHolidaysUseCase(get()) }
    factory { MainViewModel(get()) }
}