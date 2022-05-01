package dev.adriankuta.callmonitor.model

data class CallLog(
    /**
     * The date the call occured, in milliseconds since the epoch
     */
    val beginning: Long,
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
