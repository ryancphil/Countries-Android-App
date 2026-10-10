package com.example.vexquiz.core.di

import com.example.vexquiz.core.data.HttpClientFactory
import com.example.vexquiz.data.CountriesRemoteDataSource
import com.example.vexquiz.domain.CountriesRepository
import com.example.vexquiz.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClientFactory().build()
    }

    single {
        CountriesRemoteDataSource(get())
    }

    single {
        CountriesRepository(get())
    }

    viewModelOf(::MainViewModel)
}