package com.anonymous.berlinclock.core.util

fun Int.isEven() = this % 2 == 0
fun Int.getReminder(divisor: Int) = this % divisor
fun Int.getQuotient(divisor: Int) = this / divisor
fun Int.isMultipleOfThree() = this % 3 == 0