package com.example.vexquiz.presentation

import com.example.vexquiz.domain.Country

data class MainState(
    val isLoading: Boolean = true,
    val error: String? = null,
    val countries: List<Country> = emptyList(),
    val selectedCountry: Country? = null
)
