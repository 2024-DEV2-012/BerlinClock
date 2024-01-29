package com.anonymous.berlinclock.featureberlinclock.presentation.berlinclock

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anonymous.berlinclock.featureberlinclock.domain.usecase.GetClockData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClockViewModel @Inject constructor(
    private val useCase: GetClockData
) : ViewModel() {

    private val _clockState = MutableStateFlow(ClockState())
    val clockState = _clockState.asStateFlow()
    private var job: Job? = null

    fun onEvent(event: ClockEvent) {
        when (event) {
            is ClockEvent.StartAutomaticClock -> startAutomaticClock()
            is ClockEvent.UpdateClock -> updateBerlinTime(time = event.time)
            is ClockEvent.StopAutomaticClock -> stopAutomaticClock()
        }
    }

    private fun startAutomaticClock() {
        job?.cancel()
        job = viewModelScope.launch {
            useCase()
                .onEach { result ->
                    result.let {
                        _clockState.value = _clockState.value.copy(
                            secondLamp = it.secondLamp,
                            topHourLamps = it.topHourLamps,
                            bottomHourLamps = it.bottomHourLamps,
                            topMinuteLamps = it.topMinuteLamps,
                            bottomMinuteLamps = it.bottomMinuteLamps,
                            normalTime = it.normalTime
                        )
                    }
                }.launchIn(this)
        }
    }

    private fun updateBerlinTime(time: String) {
        val result = useCase(time = time)
        result.let {
            _clockState.value = _clockState.value.copy(
                secondLamp = it.secondLamp,
                topHourLamps = it.topHourLamps,
                bottomHourLamps = it.bottomHourLamps,
                topMinuteLamps = it.topMinuteLamps,
                bottomMinuteLamps = it.bottomMinuteLamps,
                normalTime = it.normalTime
            )
        }
    }

    private fun stopAutomaticClock() {
        job?.cancel()
        _clockState.value = ClockState()
    }
}
