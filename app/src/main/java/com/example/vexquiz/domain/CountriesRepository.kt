package com.example.vexquiz.domain

import com.example.vexquiz.core.domain.DataError
import com.example.vexquiz.core.domain.Result
import com.example.vexquiz.core.domain.map
import com.example.vexquiz.data.CountriesRemoteDataSource
import com.example.vexquiz.data.toDomain

class CountriesRepository(
    val dataSource: CountriesRemoteDataSource
) {

    suspend fun getCountries(): Result<List<Country>, DataError.Network> {
        return dataSource.getCountries().map { countries ->
            countries.map { it.toDomain() }
        }
    }
}