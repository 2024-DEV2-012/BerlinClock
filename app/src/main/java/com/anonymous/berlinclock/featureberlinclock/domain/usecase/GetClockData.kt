package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.BOTTOM_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.BottomHourLamps
import com.anonymous.berlinclock.core.util.BottomMinuteLamps
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TIME_DELIMITER
import com.anonymous.berlinclock.core.util.TIME_FORMAT
import com.anonymous.berlinclock.core.util.TIME_MAX_VALUE
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.anonymous.berlinclock.core.util.TOP_HOUR_LAMP_VALUE
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_VALUE
import com.anonymous.berlinclock.core.util.TopHourLamps
import com.anonymous.berlinclock.core.util.TopMinuteLamps
import com.anonymous.berlinclock.core.util.formattedDate
import com.anonymous.berlinclock.core.util.getQuotient
import com.anonymous.berlinclock.core.util.getReminder
import com.anonymous.berlinclock.core.util.isEven
import com.anonymous.berlinclock.core.util.isMultipleOfThree
import com.anonymous.berlinclock.core.util.splitIntoIntParts
import com.anonymous.berlinclock.featureberlinclock.domain.model.BerlinClock
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.joda.time.DateTime

class GetClockData {

    operator fun invoke(): Flow<BerlinClock> = flow {
        while (true) {
            val formattedTime = DateTime.now().formattedDate(TIME_FORMAT)
            emit(invoke(formattedTime))
            delay(DELAY)
        }
    }

    operator fun invoke(time: String): BerlinClock {
        val (hour, min, sec) = time.splitIntoIntParts(TIME_DELIMITER)
        return BerlinClock(
            secondLamp = getSecondLamp(sec),
            topHourLamps = getTopHourLamps(hour),
            bottomHourLamps = getBottomHourLamps(hour),
            topMinuteLamps = getTopMinuteLamps(min),
            bottomMinuteLamps = getBottomMinuteLamps(min),
            normalTime = time
        )
    }

    fun getSecondLamp(seconds: Int): SecondLamp {
        checkValidInputBounds(seconds)
        return if (seconds.isEven()) LampColour.YELLOW else LampColour.OFF
    }

    fun getTopHourLamps(hour: Int): TopHourLamps {
        checkValidInputBounds(
            hour,
            maxValue = HOUR_MAX_VALUE,
            upperBoundMsg = MESSAGE_INPUT_GREATER_THAN_23
        )
        val lamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        val litLampCount = hour.getQuotient(TOP_HOUR_LAMP_VALUE)
        for (i in 0 until litLampCount) {
            lamps[i] = LampColour.RED
        }
        return lamps
    }

    fun getBottomHourLamps(hour: Int): BottomHourLamps {
        checkValidInputBounds(
            hour,
            maxValue = HOUR_MAX_VALUE,
            upperBoundMsg = MESSAGE_INPUT_GREATER_THAN_23
        )
        val lamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        val litLampCount = hour.getReminder(TOP_HOUR_LAMP_VALUE)
        for (i in 0 until litLampCount) {
            lamps[i] = LampColour.RED
        }
        return lamps
    }

    fun getTopMinuteLamps(minutes: Int): TopMinuteLamps {
        checkValidInputBounds(minutes)
        val lamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        val litLampCount = minutes.getQuotient(TOP_MIN_LAMP_VALUE)
        for (i in 0 until litLampCount) {
            lamps[i] = if ((i + 1).isMultipleOfThree()) LampColour.RED else LampColour.YELLOW
        }
        return lamps
    }

    fun getBottomMinuteLamps(minutes: Int): BottomMinuteLamps {
        checkValidInputBounds(minutes)
        val lamps = MutableList(BOTTOM_MIN_LAMP_COUNT) { LampColour.OFF }
        val litLampCount = minutes.getReminder(TOP_MIN_LAMP_VALUE)
        for (i in 0 until litLampCount) {
            lamps[i] = LampColour.YELLOW
        }
        return lamps
    }

    private fun checkValidInputBounds(
        inputTime: Int,
        maxValue: Int = TIME_MAX_VALUE,
        upperBoundMsg: String = MESSAGE_INPUT_GREATER_THAN_59
    ) {
        if (inputTime < TIME_MIN_VALUE || inputTime > maxValue) {
            throw RuntimeException(
                if (inputTime < TIME_MIN_VALUE) MESSAGE_INPUT_LESS_THAN_0 else upperBoundMsg
            )
        }
    }

    companion object {
        const val DELAY: Long = 1000
    }

}