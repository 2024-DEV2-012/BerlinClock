package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.MainDispatcherRule
import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.featureberlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.featureberlinclock.domain.usecase.GetClockData
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class ClockViewModelTest {

    private lateinit var viewModel: ClockViewModel
    private var useCase = mockk<GetClockData>()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `check berlin clock lamps are updating for the automatic clock scenario`() = runTest {
        viewModel = ClockViewModel(useCase)
        val secondLamp = LampColour.YELLOW
        val topHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        val bottomHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        val topMinuteLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.YELLOW }
        val bottomMinuteLamps = List(BOTTOM_MIN_LAMP_COUNT) { LampColour.YELLOW }
        val normalTime = "11:12:08"
        val expectedClockState = BerlinClock(
            secondLamp = secondLamp,
            topHourLamps = topHourLamps,
            bottomHourLamps = bottomHourLamps,
            topMinuteLamps = topMinuteLamps,
            bottomMinuteLamps = bottomMinuteLamps,
            normalTime = normalTime
        )
        every { useCase() } returns flowOf(expectedClockState)
        viewModel.onEvent(ClockEvent.StartAutomaticClock)
        val clockState = viewModel.clockState.value
        clockState.let {
            assertThat(
                it.secondLamp == secondLamp &&
                        it.topHourLamps == topHourLamps &&
                        it.bottomHourLamps == bottomHourLamps &&
                        it.topMinuteLamps == topMinuteLamps &&
                        it.bottomMinuteLamps == bottomMinuteLamps
            ).isTrue()
        }

    }

    @Test
    fun `check berlin clock lamps are updating for the manual clock scenario`() {
        viewModel = ClockViewModel(useCase)
        val secondLamp = LampColour.YELLOW
        val topHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        val bottomHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        val topMinuteLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.YELLOW }
        val bottomMinuteLamps = List(BOTTOM_MIN_LAMP_COUNT) { LampColour.YELLOW }
        val normalTime = "11:12:08"
        val expectedClock = BerlinClock(
            secondLamp = secondLamp,
            topHourLamps = topHourLamps,
            bottomHourLamps = bottomHourLamps,
            topMinuteLamps = topMinuteLamps,
            bottomMinuteLamps = bottomMinuteLamps,
            normalTime = normalTime
        )
        every { useCase(any()) } returns expectedClock
        viewModel.onEvent(ClockEvent.UpdateClock("11:12:08"))
        val clockState = viewModel.clockState.value
        clockState.let {
            assertThat(
                it.secondLamp == secondLamp &&
                        it.topHourLamps == topHourLamps &&
                        it.bottomHourLamps == bottomHourLamps &&
                        it.topMinuteLamps == topMinuteLamps &&
                        it.bottomMinuteLamps == bottomMinuteLamps
            ).isTrue()
        }
    }
}