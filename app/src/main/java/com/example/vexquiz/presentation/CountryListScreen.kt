package com.example.vexquiz.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.vexquiz.MainStatePreviewProvider
import com.example.vexquiz.designsystem.theme.VexQuizTheme
import com.example.vexquiz.domain.MainAction
import org.koin.androidx.compose.koinViewModel

@Composable
fun CountryListScreenRoot(
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CountryListScreen(
        state = state,
        onAction = viewModel::onAction
    )
}

@Composable
private fun CountryListScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        val errorMessage = state.error
        BackHandler(enabled = state.selectedCountry != null) {
            onAction(MainAction.DismissDetail)
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "LOADING...")
            }
        } else if (errorMessage != null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = errorMessage)
                Button(
                    onClick = {
                        onAction(MainAction.Refresh)
                    }
                ) {
                    Text(text = "Retry")
                }
            }
        } else if (state.selectedCountry != null) {
            CountryDetailScreen(
                country = state.selectedCountry
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                items(state.countries) {
                    CountryRowItem(
                        country = it,
                        modifier = Modifier.clickable(
                            role = Role.Button,
                            onClick = {
                                onAction(MainAction.Select(it))
                            }
                        )
                    )
                }
            }
        }
    }
}

@Preview(name = "CountryList", showBackground = true)
@Composable
private fun CountryListScreenPreview(
    @PreviewParameter(provider = MainStatePreviewProvider::class) state: MainState,
) {
    VexQuizTheme {
        CountryListScreen(
            state = state,
            onAction = {}
        )
    }
}