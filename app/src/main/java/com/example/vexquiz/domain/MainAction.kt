package com.example.vexquiz.domain

interface MainAction {
    data object Refresh: MainAction
    data class Select(val country: Country): MainAction
    data object DismissDetail: MainAction
}