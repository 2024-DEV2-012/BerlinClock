package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.SEC_MAX_VALUE
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class GetClockDataTest {
    private lateinit var getClockData: GetClockData

    @Before
    fun setUp() {
        getClockData = GetClockData()
    }

    @Test
    fun `seconds converter throws exception when the input is less than min value`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSecondLamp(seconds = TIME_MIN_VALUE - 1)
        }
        assertThat(exception).hasMessageThat()
            .contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `seconds converter throws exception when the input is greater than max value`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSecondLamp(seconds = SEC_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat()
            .contains(MESSAGE_INPUT_GREATER_THAN_59)
    }

    @Test
    fun `second lamb is OFF for all the odd seconds`() {
        (TIME_MIN_VALUE + 1..SEC_MAX_VALUE step 2).forEach {
            assertThat(getClockData.getSecondLamp(seconds = it) == LampColour.OFF).isTrue()
        }
    }


    @Test
    fun `second lamb is ON for all the even seconds`() {
        (TIME_MIN_VALUE..SEC_MAX_VALUE step 2).forEach {
            assertThat(getClockData.getSecondLamp(seconds = it) == LampColour.YELLOW).isTrue()
        }
    }

    @Test
    fun `top hour lamps converter throws exception when the input is less than min value 0`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getTopHourLamps(hour = TIME_MIN_VALUE - 1)
        }
        assertThat(exception).hasMessageThat()
            .contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `top hour lamps converter throws exception when the input is greater than max value 23`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getTopHourLamps(hour = 24)
        }
        assertThat(exception).hasMessageThat()
            .contains("The input is greater than the maximum value, which is 23")
    }
}