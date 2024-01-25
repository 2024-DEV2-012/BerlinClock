package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.BottomHourLamps
import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.TopHourLamps
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.TIME_MAX_VALUE
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.anonymous.berlinclock.core.util.TOP_HOUR_LAMP_VALUE
import com.anonymous.berlinclock.core.util.TOP_MIN_LAMP_COUNT
import com.anonymous.berlinclock.core.util.TopMinuteLamps
import com.anonymous.berlinclock.core.util.getQuotient
import com.anonymous.berlinclock.core.util.getReminder
import com.anonymous.berlinclock.core.util.isEven
import com.anonymous.berlinclock.core.util.isMultipleOfThree

class GetClockData {

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
        val litLambCount = hour.getQuotient(TOP_HOUR_LAMP_VALUE)
        for (i in 0 until litLambCount) {
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
        val litLambCount = hour.getReminder(TOP_HOUR_LAMP_VALUE)
        for (i in 0 until litLambCount) {
            lamps[i] = LampColour.RED
        }
        return lamps
    }

    fun getTopMinuteLamps(minutes: Int): TopMinuteLamps {
        checkValidInputBounds(minutes)
        val lamps = MutableList(TOP_MIN_LAMP_COUNT) { LampColour.OFF }
        val litLambCount = minutes.getQuotient(5)
        for (i in 0 until litLambCount) {
            lamps[i] = if ((i + 1).isMultipleOfThree()) LampColour.RED else LampColour.YELLOW
        }
        return lamps
    }

    fun getBottomMinuteLamps(minutes: Int): List<LampColour> {
        checkValidInputBounds(minutes)
        val lamps = MutableList(4) { LampColour.OFF }
        if (minutes in (1..59 step 5)) {
            lamps[0] = LampColour.YELLOW
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

}