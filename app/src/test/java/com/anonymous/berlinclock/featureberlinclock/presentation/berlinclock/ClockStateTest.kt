package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class ClockStateTest {
    private lateinit var clockState: ClockState

    @Before
    fun setUp() {
        clockState = ClockState()
    }


    @Test
    fun `check secondLamp is initially OFF`() {
        assertThat(clockState.secondLamp == LampColour.OFF).isTrue()
    }

    @Test
    fun `check top hour lamps are initially OFF`() {
        assertThat(clockState.topHourLamps == List(HOUR_LAMP_COUNT) { LampColour.OFF }).isTrue()
    }

    @Test
    fun `check bottom hour lamps are initially OFF`() {
        assertThat(clockState.bottomHourLamps == List(HOUR_LAMP_COUNT) { LampColour.OFF }).isTrue()
    }

    @Test
    fun `check top minute lamps are initially OFF`() {
        assertThat(clockState.topMinuteLamps == List(TOP_MIN_LAMP_COUNT) { LampColour.OFF }).isTrue()
    }

    @Test
    fun `check bottom minute lamps are initially OFF`() {
        assertThat(clockState.bottomMinuteLamps == List(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }).isTrue()
    }
}
