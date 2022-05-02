package dev.adriankuta.callmonitor.repositories.calls

import android.content.Context
import android.database.Cursor
import android.provider.CallLog.Calls
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.adriankuta.callmonitor.repositories.entities.CallLogEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CallRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CallRepository {

    private val queryColumns = arrayOf(
        Calls._ID,
        Calls.DATE,
        Calls.DURATION,
        Calls.NUMBER,
        Calls.CACHED_NAME
    )

    private val sortOrder = "${Calls.DATE} DESC"

    private fun mapToEntity(cursor: Cursor): CallLogEntity {
        return CallLogEntity(
            date = cursor.getLong(cursor.getColumnIndexOrThrow(Calls.DATE)),
            duration = cursor.getInt(cursor.getColumnIndexOrThrow(Calls.DURATION)),
            number = cursor.getString(cursor.getColumnIndexOrThrow(Calls.NUMBER)),
            name = cursor.getString(cursor.getColumnIndexOrThrow(Calls.CACHED_NAME))
        )
    }

    override fun getCallHistory(startTime: Long): List<CallLogEntity> {
        if(startTime == -1L) {
            return emptyList()
        }

        val filter = "${Calls.DATE}>$startTime"
        val cursor = context.contentResolver.query(
            Calls.CONTENT_URI, queryColumns, filter, null, sortOrder
        )

        cursor?.use {
            return generateSequence { if (it.moveToNext()) it else null }
                .map(this::mapToEntity)
                .toList()
        }
        return emptyList()
    }
}