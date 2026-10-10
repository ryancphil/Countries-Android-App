package com.example.vexquiz

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.vexquiz.data.DummyData
import com.example.vexquiz.domain.Country

class CountryPreviewProvider: PreviewParameterProvider<Country> {
    override val values: Sequence<Country> = sequenceOf(
        DummyData.usa,
        DummyData.japan,
        DummyData.canada
    )
}