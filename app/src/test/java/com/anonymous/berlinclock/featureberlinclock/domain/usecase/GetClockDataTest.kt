package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows
import org.junit.Test

class GetClockDataTest {
    private lateinit var getClockData: GetClockData

    @Test
    fun `seconds converter throws exception when the input is less than 0`() {
        getClockData = GetClockData()
        val exception = assertThrows(RuntimeException::class.java) {
            getClockData.getSeconds(seconds = -1)
        }
        assertThat(exception).hasMessageThat()
            .contains("The input is less than the minimum value, which is 0")
    }
}