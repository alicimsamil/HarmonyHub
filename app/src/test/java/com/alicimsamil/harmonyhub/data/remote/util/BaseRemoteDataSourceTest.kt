package com.alicimsamil.harmonyhub.data.remote.util

import com.alicimsamil.harmonyhub.MainCoroutineRule
import com.alicimsamil.harmonyhub.core.common.util.Error
import com.alicimsamil.harmonyhub.core.data.Errors
import com.alicimsamil.harmonyhub.core.common.util.Success
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.net.SocketTimeoutException

class BaseRemoteDataSourceTest {

    private lateinit var dataSource: BaseRemoteDataSource

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        dataSource = BaseRemoteDataSource()
    }

    @Test
    fun perform_network_call_should_return_success_with_correct_value() = runTest {
        val result = dataSource.performApiCall { Response.success(44) }
        assertTrue(result is Success)
        assertEquals(44, (result as Success).data)
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_400_codes() = runTest {
        val result = dataSource.performApiCall { Response.error<String>(400, ResponseBody.create("text/plain".toMediaTypeOrNull(),"Client error")) }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(Errors.CLIENT_ERROR, it)
        }
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_500_codes() = runTest {
        val result = dataSource.performApiCall { Response.error<String>(500, ResponseBody.create("text/plain".toMediaTypeOrNull(),"Server error")) }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(Errors.SERVER_ERROR, it)
        }
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_SocketTimeoutException() = runTest {
        val mockCall: suspend () -> Response<String> = { throw SocketTimeoutException() }

        val result = dataSource.performApiCall { mockCall() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(Errors.SLOW_INTERNET_CONNECTION, it)
        }
    }
}