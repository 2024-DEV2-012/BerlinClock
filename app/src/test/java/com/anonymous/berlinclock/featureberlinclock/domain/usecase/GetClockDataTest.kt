package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.LampColour
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
    fun `seconds converter throws exception when the input is less than 0`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSecondLamp(seconds = -1)
        }
        assertThat(exception).hasMessageThat()
            .contains("The input is less than the minimum value, which is 0")
    }

    @Test
    fun `seconds converter throws exception when the input is greater than 59`() {
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSecondLamp(seconds = 60)
        }
        assertThat(exception).hasMessageThat()
            .contains("The input is greater than the maximum value, which is 59")
    }

    @Test
    fun `second lamb is OFF for all the odd seconds`() {
        (1..59 step 2).forEach {
            assertThat(getClockData.getSecondLamp(seconds = it) == LampColour.OFF).isTrue()
        }
    }


    @Test
    fun `second lamb is ON for all the even seconds`() {
        (0..59 step 2).forEach {
            assertThat(getClockData.getSecondLamp(seconds = it) == LampColour.YELLOW).isTrue()
        }
    }
}