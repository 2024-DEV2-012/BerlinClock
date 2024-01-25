package com.anonymous.berlinclock.featureberlinclock.domain.usecase

class GetClockData {

    fun getSeconds(seconds: Int) {
        if (seconds < 0) {
            throw RuntimeException("The input is less than the minimum value, which is 0")
        }

        if (seconds > 59) {
            throw RuntimeException("The input is greater than the maximum value, which is 59")
        }
    }
}