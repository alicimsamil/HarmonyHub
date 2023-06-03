package com.alicimsamil.harmonyhub.data.local.util

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import com.alicimsamil.harmonyhub.MainCoroutineRule
import com.alicimsamil.harmonyhub.core.data.Errors
import com.alicimsamil.harmonyhub.core.data.Success
import com.alicimsamil.harmonyhub.core.data.Error
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class BaseLocalDataSourceTest {

    private lateinit var dataSource: BaseLocalDataSource

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        dataSource = BaseLocalDataSource()
    }

    @Test
    fun perform_database_operation_should_return_success_with_correct_value() = runTest {
        val result = dataSource.performDatabaseOperation { 42 }
        assertTrue(result is Success)
        assertEquals(42, (result as Success).data)
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_SQLiteException() = runTest {
        val result = dataSource.performDatabaseOperation { throw SQLiteException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(Errors.INVALID_TABLE_NAME_ERROR, it)
        }
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_StaleDataException() = runTest {
        val result = dataSource.performDatabaseOperation { throw StaleDataException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(Errors.STALE_DATA_ERROR, it)
        }
    }

    @Test
    fun perform_database_operation_should_return_correct_message_for_null_error_message() = runTest {
        val result = dataSource.performDatabaseOperation { throw IOException() }
        result.onFailure {
            assertEquals(Errors.DEFAULT_ERROR, it)
        }
    }
}