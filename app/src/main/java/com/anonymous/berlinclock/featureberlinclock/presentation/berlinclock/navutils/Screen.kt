package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock.navutils

sealed class Screen(val route: String) {
    data object ClockScreen : Screen("clock_screen")
}
