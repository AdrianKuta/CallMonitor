package dev.adriankuta.callmonitor.repositories.entities

data class CallLogEntity(
    /**
     * The date the call occured, in milliseconds since the epoch
     */
    val date: Long,
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
    val name: String?

)
