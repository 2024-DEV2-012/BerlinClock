package com.anonymous.berlinclock.featureberlinclock.presentation.navutils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.ClockScreen

@Composable
fun BerlinClockNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.ClockScreen.route
    ) {
        composable(Screen.ClockScreen.route) {
            ClockScreen()
        }
    }
}