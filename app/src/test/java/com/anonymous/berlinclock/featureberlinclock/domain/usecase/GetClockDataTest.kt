package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.TIME_MAX_VALUE
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.featureberlinclock.domain.model.BerlinClock
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
            getClockData.getSecondLamp(seconds = TIME_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_59)
    }

    @Test
    fun `second lamp is OFF for all the odd seconds`() {
        (TIME_MIN_VALUE + 1..TIME_MAX_VALUE step 2).forEach {
            assertThat(getClockData.getSecondLamp(seconds = it) == LampColour.OFF).isTrue()
        }
    }


    @Test
    fun `second lamp is ON for all the even seconds`() {
        (TIME_MIN_VALUE..TIME_MAX_VALUE step 2).forEach {
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
    fun `all top hour lamps are OFF at midnight - 0 hour`() {
        val expectedResult = List(HOUR_LAMP_COUNT) { LampColour.OFF }
        assertThat(getClockData.getTopHourLamps(hour = 0) == expectedResult).isTrue()
    }

    @Test
    fun `first top hour lamp is ON when hour is 5`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.RED
        assertThat(getClockData.getTopHourLamps(hour = 5) == expectedLamps).isTrue()
    }

    @Test
    fun `first top hour lamp is ON when hour is from 5 to 9`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.RED
        (5..9).forEach {
            assertThat(getClockData.getTopHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first two top hour lamps are ON when hour is 10`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
        }
        assertThat(getClockData.getTopHourLamps(hour = 10) == expectedLamps).isTrue()
    }

    @Test
    fun `first two top hour lamps are ON when hour is from 10 to 14`() {
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
    fun `first three top hour lamps are ON when hour is from 15 to 19`() {
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
    fun `all top hour lamps are ON when hour is from 20 to 23`() {
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
    fun `all bottom  hour lamps are OFF when reminder for the hours divided by 5 is 0`() {
        val expectedResult = List(HOUR_LAMP_COUNT) { LampColour.OFF }
        (0..23 step 5).forEach {
            assertThat(getClockData.getBottomHourLamps(hour = it) == expectedResult).isTrue()
        }
    }

    @Test
    fun `first bottom hour lamp is ON when reminder for the hours divided by 5 is 1`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.RED
        (1..23 step 5).forEach {
            assertThat(getClockData.getBottomHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first two bottom hour lamps are ON when reminder for the hours divided by 5 is 2`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
        }
        (2..23 step 5).forEach {
            assertThat(getClockData.getBottomHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first three bottom hour lamps are ON when reminder for the hours divided by 5 is 3`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.RED
            this[1] = LampColour.RED
            this[2] = LampColour.RED
        }
        (3..23 step 5).forEach {
            assertThat(getClockData.getBottomHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `all the four bottom hour lamps are ON when hour is 4`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.RED }
        assertThat(getClockData.getBottomHourLamps(hour = 4) == expectedLamps).isTrue()
    }

    @Test
    fun `all the four bottom hour lamps are ON when reminder for the hours divided by 5 is 4`() {
        val expectedLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.RED }
        (4..23 step 5).forEach {
            assertThat(getClockData.getBottomHourLamps(hour = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `top minute lamp converter throws exception when the input is less than min value 0`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getTopMinuteLamps(minutes = TIME_MIN_VALUE - 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `top minute lamp converter throws exception when the input is greater than max value 59`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getTopMinuteLamps(minutes = TIME_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_59)
    }

    @Test
    fun `all top minute lamps are OFF when minutes is in the range from 0 to 4`() {
        val expectedResult = List(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        (0..4).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedResult).isTrue()
        }
    }

    @Test
    fun `first top minute lamp is ON when minutes is in the range from 5 to 9`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.YELLOW
        (5..9).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first two top minute lamps are ON when minutes is in the range from 10 to 14`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.YELLOW
            this[1] = LampColour.YELLOW
        }
        (10..14).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first three top minute lamps are ON and third lamp is RED when minutes is in the range from 15 to 19`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.YELLOW
            this[1] = LampColour.YELLOW
            this[2] = LampColour.RED
        }
        (15..19).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first four top minute lamps are ON and third lamp is RED when minutes is in the range from 20 to 24`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.YELLOW
            this[1] = LampColour.YELLOW
            this[2] = LampColour.RED
            this[3] = LampColour.YELLOW
        }
        (20..24).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first five top minute lamps are ON and third lamp is RED when minutes is in the range from 25 to 29`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.YELLOW
            this[1] = LampColour.YELLOW
            this[2] = LampColour.RED
            this[3] = LampColour.YELLOW
            this[4] = LampColour.YELLOW
        }
        (25..29).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first six top minute lamps are ON and third and sixth lamps are RED when minutes is in the range from 30 to 34`() {
        val expectedLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps.apply {
            this[0] = LampColour.YELLOW
            this[1] = LampColour.YELLOW
            this[2] = LampColour.RED
            this[3] = LampColour.YELLOW
            this[4] = LampColour.YELLOW
            this[5] = LampColour.RED
        }
        (30..34).forEach {
            assertThat(getClockData.getTopMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `bottom minute lamp converter throws exception when the input is less than min value 0`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getBottomMinuteLamps(minutes = TIME_MIN_VALUE - 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_LESS_THAN_0)
    }

    @Test
    fun `bottom minute lamp converter throws exception when the input is greater than max value 59`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getBottomMinuteLamps(minutes = TIME_MAX_VALUE + 1)
        }
        assertThat(exception).hasMessageThat().contains(MESSAGE_INPUT_GREATER_THAN_59)
    }

    @Test
    fun `all bottom minute lamps are OFF when reminder for the minutes divided by 5 is 0`() {
        val expectedResult = List(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        (0..59 step 5).forEach {
            assertThat(getClockData.getBottomMinuteLamps(minutes = it) == expectedResult).isTrue()
        }
    }

    @Test
    fun `first bottom minute lamp is ON when reminder for the minutes divided by 5 is 1`() {
        val expectedLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        expectedLamps[0] = LampColour.YELLOW
        (1..59 step 5).forEach {
            assertThat(getClockData.getBottomMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first two bottom minute lamps are ON when reminder of the minutes divided by 5 is 2`() {
        val expectedLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        for (i in 0..1) {
            expectedLamps[i] = LampColour.YELLOW
        }
        (2..59 step 5).forEach {
            assertThat(getClockData.getBottomMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `first three bottom minute lamps are ON when reminder of the minutes divided by 5 is 3`() {
        val expectedLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        for (i in 0..2) {
            expectedLamps[i] = LampColour.YELLOW
        }
        (3..59 step 5).forEach {
            assertThat(getClockData.getBottomMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }

    @Test
    fun `all the four bottom minute lamps are ON when reminder of the minutes divided by 5 is 4`() {
        val expectedLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.YELLOW }
        (4..59 step 5).forEach {
            assertThat(getClockData.getBottomMinuteLamps(minutes = it) == expectedLamps).isTrue()
        }
    }


    @Test
    fun `should take the time 0 in string format and returns corresponding aggregate berlin time`() {
        val timeString = "00:00:00"
        val expectedRes = BerlinClock(
            secondLamp = LampColour.YELLOW,
            topHourLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF },
            bottomHourLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF },
            topMinuteLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF },
            bottomMinuteLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF },
            normalTime = timeString
        )
        val berlinClock = getClockData(time = timeString)
        assertThat(berlinClock == expectedRes).isTrue()
    }

    @Test
    fun `should take a random time in string format and returns corresponding aggregate berlin time`() {
        val timeString = "21:16:01" // in hh:mm:ss format
        val topHourLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.RED }
        val bottomHourLamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        bottomHourLamps[0] = LampColour.RED
        val topMinLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        topMinLamps[0] = LampColour.YELLOW
        topMinLamps[1] = LampColour.YELLOW
        topMinLamps[2] = LampColour.RED
        val bottomMinLamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        bottomMinLamps[0] = LampColour.YELLOW
        val expectedRes = BerlinClock(
            secondLamp = LampColour.OFF,
            topHourLamps = topHourLamps,
            bottomHourLamps = bottomHourLamps,
            topMinuteLamps = topMinLamps,
            bottomMinuteLamps = bottomMinLamps,
            normalTime = timeString
        )
        val berlinClock = getClockData(time = timeString)
        assertThat(berlinClock == expectedRes).isTrue()
    }

}