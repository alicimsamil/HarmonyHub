package com.alicimsamil.harmonyhub.data.remote.util

import com.alicimsamil.harmonyhub.BuildConfig
import com.alicimsamil.harmonyhub.core.common.util.DataResult
import com.alicimsamil.harmonyhub.core.common.util.Success
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.core.data.Errors
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * This class represents a base remote data source that manages network calls to remote data sources
 * and handles error conditions.
 */
open class BaseRemoteDataSource {
    suspend fun <T> performApiCall(
        call: suspend () -> Response<T>
    ): DataResult<T, String> {
        return try {
            val response = call.invoke()
            if(response.isSuccessful) {
                Success(response.body())
            } else {
                throw HttpException(response)
            }
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG)
                exception.printStackTrace()
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): DataResult<Nothing, String> {
        return when(exception) {
            is HttpException -> {
                when (exception.code()) {
                    in 400..451 -> Error( Errors.CLIENT_ERROR )

                    in 500..599 -> Error( Errors.SERVER_ERROR )

                    else -> Error( Errors.UNEXPECTED_ERROR )
                }
            }

            is UnknownHostException -> Error( Errors.NO_INTERNET_CONNECTION )

            is SocketTimeoutException -> Error( Errors.SLOW_INTERNET_CONNECTION )

            else -> Error(exception.message ?: Errors.DEFAULT_ERROR )
        }
    }
}