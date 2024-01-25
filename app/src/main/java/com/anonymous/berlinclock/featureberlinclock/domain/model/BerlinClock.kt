package com.anonymous.berlinclock.featureberlinclock.domain.model

import com.anonymous.berlinclock.core.util.BottomHourLamps
import com.anonymous.berlinclock.core.util.BottomMinuteLamps
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TopHourLamps
import com.anonymous.berlinclock.core.util.TopMinuteLamps

data class BerlinClock(
    val secondLamp: SecondLamp,
    val topHourLamps: TopHourLamps,
    val bottomHourLamps: BottomHourLamps,
    val topMinuteLamps: TopMinuteLamps,
    val bottomMinuteLamps: BottomMinuteLamps,
    val normalTime: String
)
