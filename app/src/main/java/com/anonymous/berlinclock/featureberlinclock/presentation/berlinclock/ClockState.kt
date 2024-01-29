package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.BottomHourLamps
import com.anonymous.berlinclock.core.util.BottomMinuteLamps
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.INITIAL_NORMAL_TIME
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.TopHourLamps
import com.anonymous.berlinclock.core.util.TopMinuteLamps

data class ClockState(
    val secondLamp: SecondLamp = LampColour.OFF,
    val topHourLamps: TopHourLamps = List(HOUR_LAMP_COUNT) { LampColour.OFF },
    val bottomHourLamps: BottomHourLamps = List(HOUR_LAMP_COUNT) { LampColour.OFF },
    val topMinuteLamps: TopMinuteLamps = List(TOP_MIN_LAMP_COUNT) { LampColour.OFF },
    val bottomMinuteLamps: BottomMinuteLamps = List(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF },
    val normalTime: String = INITIAL_NORMAL_TIME
)
