package com.example.vexquiz

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.vexquiz.data.DummyData
import com.example.vexquiz.presentation.MainState
import kotlin.collections.emptyList

class MainStatePreviewProvider : PreviewParameterProvider<MainState> {
    override val values: Sequence<MainState> = sequenceOf(
        // 1. Loading State
        MainState(isLoading = true),

        // 2. Success State (Populated)
        MainState(
            isLoading = false,
            countries = listOf(DummyData.usa, DummyData.canada, DummyData.japan)
        ),

        // 3. Error State
        MainState(
            isLoading = false,
            error = "No internet connection"
        ),

        // 4. Empty State
        MainState(
            isLoading = false,
            countries = emptyList()
        )
    )
}