package com.alicimsamil.harmonyhub.core.domain

import java.util.concurrent.TimeUnit

/**
 * This TimeConverter class provides a function,
 * "convert" which takes a time value in milliseconds
 * and returns a string representation in minutes and seconds.
 */
object TimeConverter {
    fun convert(millisecond: Long) : String{
        return String.format(
            "%d min, %d sec",
            TimeUnit.MILLISECONDS.toMinutes(millisecond),
            TimeUnit.MILLISECONDS.toSeconds(millisecond)
            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisecond))
        )
    }
}