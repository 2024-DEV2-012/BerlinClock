package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.LampColour
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0
import com.anonymous.berlinclock.core.util.SecondLamp
import com.anonymous.berlinclock.core.util.isEven

class GetClockData {

    fun getSecondLamp(seconds: Int): SecondLamp {
        if (seconds < 0 || seconds > 59) {
            throw RuntimeException(
                if (seconds < 0) {
                    MESSAGE_INPUT_LESS_THAN_0
                } else {
                    MESSAGE_INPUT_GREATER_THAN_59
                }
            )
        }
        return if (seconds.isEven()) LampColour.YELLOW else LampColour.OFF
    }
}