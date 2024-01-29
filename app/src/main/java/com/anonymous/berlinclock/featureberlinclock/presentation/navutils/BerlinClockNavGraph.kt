package com.anonymous.berlinclock.featureberlinclock.presentation.navutils

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.ClockScreen
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.ClockViewModel

@Composable
fun BerlinClockNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ClockScreen.route
    ) {
        composable(Screen.ClockScreen.route) {
            val viewModel = hiltViewModel<ClockViewModel>()
            val state = viewModel.clockState
            ClockScreen(state, viewModel::onEvent)
        }
    }
}