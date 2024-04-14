package com.asia.newsapp.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.asia.newsapp.ui.theme.LocalNavigationProvider
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navigateTo: (HomeUiEffect) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    HomeScreenContent(
            state = state,
            listener = viewModel
    )
}

@Composable
fun HomeScreenContent(
    state: HomeUiState,
    listener: HomeInteractionListener
) {

}