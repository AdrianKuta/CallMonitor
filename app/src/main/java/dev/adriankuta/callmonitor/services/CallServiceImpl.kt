package dev.adriankuta.callmonitor.services

import android.content.Context
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class CallServiceImpl @Inject constructor(
    @ActivityContext private val context: Context
): CallService {
    override fun getCallHistory() {
    }
}