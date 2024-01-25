package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import android.content.pm.LauncherApps
import com.anonymous.berlinclock.core.util.HOUR_MAX_VALUE
import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_23
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.SEC_MAX_VALUE
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.TIME_MIN_VALUE
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

    fun getTopHourLamps(hour: Int): List<LampColour> {
        if (hour < TIME_MIN_VALUE || hour > HOUR_MAX_VALUE) {
            throw RuntimeException(
                if (hour < TIME_MIN_VALUE) MESSAGE_INPUT_LESS_THAN_0 else MESSAGE_INPUT_GREATER_THAN_23
            )
        }
        val lamps = MutableList(4) { LampColour.OFF }
        if (hour in (5..9)) {
            lamps[0] = LampColour.RED
        } else if(hour == 10) {
            lamps[0] = LampColour.RED
            lamps[1] = LampColour.RED
        }
        return lamps
    }

}