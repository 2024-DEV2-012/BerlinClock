package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.HOUR_LAMP_COUNT
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.HourLamps
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.SEC_MAX_VALUE
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
import com.anonymous.berlinclock.core.util.TOP_HOUR_LAMP_VALUE
import com.anonymous.berlinclock.core.util.isEven

class GetClockData {

    fun getSecondLamp(seconds: Int): SecondLamp {
        if (seconds < TIME_MIN_VALUE || seconds > SEC_MAX_VALUE) {
            throw RuntimeException(
                if (seconds < TIME_MIN_VALUE) {
                    MESSAGE_INPUT_LESS_THAN_0
                } else {
                    MESSAGE_INPUT_GREATER_THAN_59
                }
            )
        }
        return if (seconds.isEven()) LampColour.YELLOW else LampColour.OFF
    }

    fun getTopHourLamps(hour: Int): HourLamps {
        if (hour < TIME_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            throw RuntimeException(
                if (hour < TIME_MIN_VALUE) MESSAGE_INPUT_LESS_THAN_0 else MESSAGE_INPUT_GREATER_THAN_23
            )
        }
        val lamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        val litLambCount = hour / TOP_HOUR_LAMP_VALUE
        for (i in 0 until litLambCount) {
            lamps[i] = LampColour.RED
        }
        return lamps
    }

    fun getBottomHourLamps(hour: Int): List<LampColour> {
        if (hour < TIME_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            throw RuntimeException(
                if (hour < TIME_MIN_VALUE) MESSAGE_INPUT_LESS_THAN_0 else MESSAGE_INPUT_GREATER_THAN_23
            )
        }
        val lamps = MutableList(HOUR_LAMP_COUNT) { LampColour.OFF }
        if (hour == 1) {
            lamps[0] = LampColour.RED
        } else if (hour == 2) {
            lamps[0] = LampColour.RED
            lamps[1] = LampColour.RED
        } else if (hour == 3) {
            lamps[0] = LampColour.RED
            lamps[1] = LampColour.RED
            lamps[2] = LampColour.RED
        }
        return lamps
    }

}