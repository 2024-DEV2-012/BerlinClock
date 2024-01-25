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
import com.anonymous.berlinclock.core.util.isEven

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
        val litLambCount = hour / TOP_HOUR_LAMP_VALUE
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
        val litLambCount = hour % TOP_HOUR_LAMP_VALUE
        for (i in 0 until litLambCount) {
            lamps[i] = LampColour.RED
        }
        return lamps
    }

    fun getTopMinuteLamps(minutes: Int) {
        checkValidInputBounds(minutes)
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