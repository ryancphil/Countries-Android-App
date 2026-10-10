package com.example.vexquiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.example.vexquiz.data.DummyData
import com.example.vexquiz.designsystem.theme.VexQuizTheme
import com.example.vexquiz.domain.Country

@Composable
fun CountryDetailScreen(
    country: Country,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Flag Image
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = country.flag.url,
            contentDescription = "Flag of ${country.name}"
        )
        // Name
        Text(
            text = country.name,
            style = MaterialTheme.typography.headlineMedium
        )
        // Capitals
        for (cap in country.capitals) {
            Text(
                text = cap
            )
        }
    }
}

@Preview
@Composable
private fun CountryDetailScreenPreview() {
    VexQuizTheme {
        CountryDetailScreen(
            DummyData.usa
        )
    }
}