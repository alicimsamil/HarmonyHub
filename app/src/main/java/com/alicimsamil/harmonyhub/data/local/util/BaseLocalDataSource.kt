package com.alicimsamil.harmonyhub.data.local.util

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import android.os.DeadObjectException
import com.alicimsamil.harmonyhub.BuildConfig
import com.alicimsamil.harmonyhub.core.data.DataResult
import com.alicimsamil.harmonyhub.core.data.Success
import com.alicimsamil.harmonyhub.core.data.Error
import com.alicimsamil.harmonyhub.core.data.Errors

/**
 * This class makes success and fail situations in database operations easier,
 * cleaner and more readable.
 */
open class BaseLocalDataSource {
    suspend fun <T> performDatabaseOperation(
        operation: suspend () -> T
    ): DataResult<T, String> {
        return try {
            Success(operation.invoke())
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG)
                exception.printStackTrace()
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): DataResult<Nothing, String> {
        return when (exception) {
            is SQLiteException -> Error( Errors.INVALID_TABLE_NAME_ERROR )

            is StaleDataException -> Error( Errors.STALE_DATA_ERROR )

            is IllegalArgumentException -> Error( Errors.INVALID_QUERY_ERROR )

            is DeadObjectException -> Error( Errors.DATABASE_TRANSACTION_ERROR )

            else -> Error(exception.message ?: Errors.DEFAULT_ERROR)
        }
    }
}