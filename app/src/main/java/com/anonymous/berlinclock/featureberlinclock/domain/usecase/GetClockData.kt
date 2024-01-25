package com.anonymous.berlinclock.featureberlinclock.domain.usecase

class GetClockData {

    fun getSeconds(seconds: Int) {
        if (seconds < 0) {
            throw RuntimeException("The input is less than the minimum value, which is 0")
        }
    }
}