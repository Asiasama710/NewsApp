package com.asia.newsapp.ui.screens.bookmarked

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.asia.newsapp.ui.screens.home.HomeUiEffect
import com.asia.newsapp.ui.theme.LocalNavigationProvider
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookmarkedScreen(
    navigateTo: (HomeUiEffect) -> Unit,
    viewModel: BookmarkedViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavigationProvider.current

    BookmarkedScreenContent(
            state = state,
            listener = viewModel
    )
}

@Composable
fun BookmarkedScreenContent(
    state: BookmarkedUiState,
    listener: BookmarkedInteractionListener
) {

}