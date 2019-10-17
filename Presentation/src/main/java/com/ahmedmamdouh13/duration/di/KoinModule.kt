package com.ahmedmamdouh13.duration.di

import androidx.room.Room
import com.ahmedmamdouh13.data.RepositoryImpl
import com.ahmedmamdouh13.data.local.ArticleDatabase
import com.ahmedmamdouh13.data.network.RetrofitService
import com.ahmedmamdouh13.domain.Repository
import com.ahmedmamdouh13.domain.interactor.HolidaysInteractor
import com.ahmedmamdouh13.domain.interactor.ProjectInteractor
import com.ahmedmamdouh13.domain.usecase.AddProjectUseCase
import com.ahmedmamdouh13.domain.usecase.GetHolidaysUseCase
import com.ahmedmamdouh13.duration.viewmodel.AddProjectViewModel
import com.ahmedmamdouh13.duration.viewmodel.HolidaysViewModel
import com.ahmedmamdouh13.duration.viewmodel.IntroViewModel
import com.ahmedmamdouh13.duration.viewmodel.ProjectViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val module = module {
    single { Retrofit.Builder()
        .baseUrl("https://calendarific.com")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .client(OkHttpClient.Builder()
            .writeTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .callTimeout(1,TimeUnit.MINUTES)
            .build())
        .build().create(RetrofitService::class.java)
    }
    single {
Room.databaseBuilder(androidContext(),
    ArticleDatabase::class.java,
    "ArticleDatabase")
    .fallbackToDestructiveMigration()
    .build().dao
    }

    single {
        Room.databaseBuilder(androidContext(),
            ArticleDatabase::class.java,
            "ArticleDatabase")
            .fallbackToDestructiveMigration()
            .build().projDao
    }
    single {
        Room.databaseBuilder(androidContext(),
            ArticleDatabase::class.java,
            "ArticleDatabase")
            .fallbackToDestructiveMigration()
            .build().tasksDao
    }
    single<Repository> { RepositoryImpl(get(), get(), get(), get()) }
    single<HolidaysInteractor> {
        GetHolidaysUseCase(
            get()
        )
    }
    single<ProjectInteractor> {
        AddProjectUseCase(
            get()
        )
    }

    factory { HolidaysViewModel(get()) }
    factory { IntroViewModel(get(), GetHolidaysUseCase(get())) }
    factory { AddProjectViewModel(get()) }
    factory { ProjectViewModel(get()) }
}