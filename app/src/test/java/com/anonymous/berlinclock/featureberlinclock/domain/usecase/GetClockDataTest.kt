package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
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
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `seconds converter throws exception when the input is greater than max value`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSecondLamp(seconds = SEC_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_59)
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
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `top hour lamps converter throws exception when the input is greater than max value 23`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getTopHourLamps(hour = HOUR_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_23)
    }

    @Test
    fun `all top hour lambs are OFF at midnight - 0 hour`() {
        val expectedResult = List(HOUR_LAMP_COUNT) { LampColour.OFF }
        assertThat(getClockData.getTopHourLamps(hour = 0) == expectedResult).isTrue()
    }

    @Test
    fun `first top hour lamb is ON when hour is 5`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.RED
        assertThat(getClockData.getTopHourLamps(hour = 5) == expectedLamps).isTrue()
    }

    @Test
    fun `first top hour lamb is ON when hour is from 5 to 9`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.RED
        (5..9).forEach {
            assertThat(getClockData.getTopHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first two top hour lambs are ON when hour is 10`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
        }
        assertThat(getClockData.getTopHourLamps(hour = 10) == expectedLamps).isTrue()
    }

    @Test
    fun `first two top hour lambs are ON when hour is from 10 to 14`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
        }
        (10..14).forEach {
            assertThat(getClockData.getTopHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first three top hour lambs are ON when hour is from 15 to 19`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
            this[2] = LampColour.RED
        }
        (15..19).forEach {
            assertThat(getClockData.getTopHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `all top hour lambs are ON when hour is from 20 to 23`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.RED }
        (20..23).forEach {
            assertThat(getClockData.getTopHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `bottom hour lamps converter throws exception when the input is less than min value 0`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getBottomHourLamps(hour = TIME_MIN_VALUE - 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `bottom hour lamps converter throws exception when the input is greater than max value 23`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getBottomHourLamps(hour = HOUR_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_23)
    }

    @Test
    fun `all bottom  hour lambs are OFF at midnight - 0 hour`() {
        val expectedResult = List(HOUR_LAMP_COUNT) { LampColour.OFF }
        assertThat(getClockData.getBottomHourLamps(hour = 0) == expectedResult).isTrue()
    }
}