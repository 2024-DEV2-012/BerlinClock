package com.anonymous.berlinclock.featureberlinclock.domain.usecase

import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_GREATER_THAN_59
import com.anonymous.berlinclock.core.util.MESSAGE_INPUT_LESS_THAN_0

class GetClockData {

    fun getSeconds(seconds: Int): String {
        if (seconds < 0 || seconds > 59) {
            throw RuntimeException(
                if (seconds < 0) {
                    MESSAGE_INPUT_LESS_THAN_0
                } else {
                    MESSAGE_INPUT_GREATER_THAN_59
                }
            )
        }
        return if (seconds % 2 == 0) "Y" else "O"
    }
}