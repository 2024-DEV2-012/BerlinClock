package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import com.anonymous.berlinclock.MainDispatcherRule
import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.BottomHourLamps
import com.anonymous.berlinclock.core.util.BottomMinuteLamps
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.TopHourLamps
import com.anonymous.berlinclock.core.util.TopMinuteLamps
import com.anonymous.berlinclock.featureberlinclock.domain.model.BerlinClock
import com.anonymous.berlinclock.featureberlinclock.domain.usecase.GetClockData
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ClockViewModelTest {

    private lateinit var viewModel: ClockViewModel
    private var useCase = mockk<GetClockData>()
    private lateinit var secondLamp: SecondLamp
    private lateinit var topHourLamps: TopHourLamps
    private lateinit var bottomHourLamps: BottomHourLamps
    private lateinit var topMinuteLamps: TopMinuteLamps
    private lateinit var bottomMinuteLamps: BottomMinuteLamps
    private lateinit var normalTime: String
    private lateinit var expectedClock: BerlinClock

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = ClockViewModel(useCase)
        secondLamp = LampColour.YELLOW
        topHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        bottomHourLamps = List(HOUR_LAMP_COUNT) { LampColour.RED }
        topMinuteLamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.YELLOW }
        bottomMinuteLamps = List(BOTTOM_MIN_LAMP_COUNT) { LampColour.YELLOW }
        normalTime = "11:12:08"
        expectedClock = BerlinClock(
            secondLamp = secondLamp,
            topHourLamps = topHourLamps,
            bottomHourLamps = bottomHourLamps,
            topMinuteLamps = topMinuteLamps,
            bottomMinuteLamps = bottomMinuteLamps,
            normalTime = normalTime
        )
    }

    @Test
    fun `check berlin clock lamps are updating for the automatic clock scenario`() = runTest {
        every { useCase() } returns flowOf(expectedClock)
        viewModel.onEvent(ClockEvent.StartAutomaticClock)
        val clockState = viewModel.clockState.value
        verifyClockState(clockState)
    }

    @Test
    fun `check stopping automatic clock scenario`() = runTest {
        every { useCase() } returns flowOf(expectedClock)
        viewModel.onEvent(ClockEvent.StartAutomaticClock)
        val clockState = viewModel.clockState.value
        verifyClockState(clockState)
        viewModel.onEvent(ClockEvent.StopAutomaticClock)
        assertThat(viewModel.clockState.value == ClockState()).isTrue()
    }

    @Test
    fun `check berlin clock lamps are updating for the manual clock scenario`() {
        every { useCase(any()) } returns expectedClock
        viewModel.onEvent(ClockEvent.UpdateClock(normalTime))
        val clockState = viewModel.clockState.value
        verifyClockState(clockState)
    }

    private fun verifyClockState(clockState: ClockState) {
        clockState.let {
            assertThat(
                it.secondLamp == secondLamp &&
                        it.topHourLamps == topHourLamps &&
                        it.bottomHourLamps == bottomHourLamps &&
                        it.topMinuteLamps == topMinuteLamps &&
                        it.bottomMinuteLamps == bottomMinuteLamps &&
                        it.normalTime == normalTime
            ).isTrue()
        }
    }
}