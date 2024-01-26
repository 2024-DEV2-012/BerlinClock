package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

sealed class ClockEvent {
    data object StartAutomaticClock : ClockEvent()
    data class UpdateClock(val time: String) : ClockEvent()
}
