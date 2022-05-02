package dev.adriankuta.callmonitor.model

import java.util.*

data class CallLog(
    /**
     * The date the call occured.
     */
    val beginning: Date,
    /**
     * The duration of the call in seconds
     */
    val duration: Int,
    /**
     * The phone number as the user entered it.
     */
    val number: String,
    /**
     * The cached name associated with the phone number, if it exists.
     */
    val name: String,
    /**
     * How many times this number was queried by user.
     */
    val timesQueried: Int
)
