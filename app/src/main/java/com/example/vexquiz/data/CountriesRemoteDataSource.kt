package com.example.vexquiz.data

import com.example.vexquiz.core.data.get
import com.example.vexquiz.core.domain.DataError
import com.example.vexquiz.core.domain.Result
import com.example.vexquiz.core.domain.map
import io.ktor.client.HttpClient

class CountriesRemoteDataSource(
    val client: HttpClient
) {

    suspend fun getCountries(): Result<List<CountryDTO>, DataError.Network> {
        return client.get<CountriesRootResponse>(
            route = "/countries/v5",
            queryParameters = mapOf(
                "response_fields" to "names.common,flag.emoji,flag.url_png,capitals.name",
                "pretty" to "true",
                "limit" to "100"
            )
        ).map {
            it.data.objects
        }
    }

    suspend fun getCanada(): Result<String, DataError.Network> {
        return client.get<String>(
            route = "/countries/v5",
            queryParameters = mapOf("q" to "Canada")
        ).map {
            it
        }
    }
}