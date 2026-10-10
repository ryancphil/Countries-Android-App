package com.example.vexquiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vexquiz.core.domain.Result
import com.example.vexquiz.domain.CountriesRepository
import com.example.vexquiz.domain.MainAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
    val countriesRepository: CountriesRepository
): ViewModel() {

    // screen state flow
    private val _state = MutableStateFlow(MainState())
    val state = _state.onStart {
        if (_state.value.countries.isEmpty()) {
            fetchCountries()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = MainState()
    )

    fun onAction(action: MainAction) {
        Timber.d("Action taken: $action")
        when (action) {
            is MainAction.Refresh -> {
                viewModelScope.launch {
                    fetchCountries()
                }
            }
            is MainAction.Select -> {
                _state.update {
                    it.copy(selectedCountry = action.country)
                }
            }
            is MainAction.DismissDetail -> {
                _state.update {
                    it.copy(selectedCountry = null)
                }
            }
        }
    }

    private suspend fun fetchCountries() {
        when (val countries = countriesRepository.getCountries()) {
            is Result.Error -> _state.update {
                it.copy(
                    isLoading = false,
                    error = countries.error.name
                )
            }
            is Result.Success -> _state.update {
                it.copy(
                    isLoading = false,
                    error = null,
                    countries = countries.data
                )
            }
        }
    }

}