package com.example.vexquiz.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vexquiz.CountryPreviewProvider
import com.example.vexquiz.designsystem.theme.VexQuizTheme
import com.example.vexquiz.domain.Country

@Composable
fun CountryRowItem(
    country: Country,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth()
            .padding(
                horizontal = 8.dp,
                vertical = 2.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                style = MaterialTheme.typography.headlineMedium,
                text = country.name
            )
            Text(
                text = country.capitals.joinToString().ifEmpty { "None" },
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        Text(
            fontSize = 48.sp,
            text = country.flag.emoji.ifBlank { "🏳️" }
        )
    }
}

@Preview(name = "CountryRowItem", showBackground = true)
@Composable
private fun CountryRowItemPreview(
    @PreviewParameter(CountryPreviewProvider ::class) state: Country
) {
    VexQuizTheme {
        CountryRowItem(
            country = state
        )
    }
}