package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.LampColour
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ClockStateTest {
    private lateinit var clockState: ClockState

    @Test
    fun `check secondLamp is initially OFF`() {
        clockState = ClockState()
        assertThat(clockState.secondLamp == LampColour.OFF).isTrue()
    }

    @Test
    fun `check top hour lamps are initially OFF`() {
        clockState = ClockState()
        assertThat(clockState.topHourLamps == List(HOUR_LAMP_COUNT) { LampColour.OFF }).isTrue()
    }
}
