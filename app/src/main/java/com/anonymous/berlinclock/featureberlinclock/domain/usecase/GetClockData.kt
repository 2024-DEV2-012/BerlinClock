package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.LampColour
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

    fun getTopHourLamps(hour: Int) {
        if (hour < TIME_MIN_VALUE) {
            throw RuntimeException(
                MESSAGE_INPUT_LESS_THAN_0
            )
        }
    }
}