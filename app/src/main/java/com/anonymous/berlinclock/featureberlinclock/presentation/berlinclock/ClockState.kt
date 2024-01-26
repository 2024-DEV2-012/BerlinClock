package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.SecondLamp

data class ClockState(
    val secondLamp: SecondLamp = LampColour.OFF
)
