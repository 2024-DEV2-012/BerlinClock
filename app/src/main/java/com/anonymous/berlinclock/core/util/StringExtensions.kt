package com.anonymous.berlinclock.core.util

fun String.splitIntoIntParts(delimiter: String) = this.split(delimiter).map { it.toInt() }
