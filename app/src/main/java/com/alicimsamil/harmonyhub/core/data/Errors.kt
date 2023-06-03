package com.alicimsamil.harmonyhub.core.data

/**
 * This error class is a structure that holds predefined
 * error messages for different error scenarios.
 */
object Errors {

    const val DEFAULT_ERROR= "An error accrued."

    //Remote Errors
    const val NO_INTERNET_CONNECTION = "No internet connection!"
    const val SLOW_INTERNET_CONNECTION = "Slow connection."
    const val CLIENT_ERROR = "Oops! The request couldn't be processed."
    const val SERVER_ERROR = "Oops! The server encountered an error."
    const val UNEXPECTED_ERROR = "Oops! Something unexpected happened."

    //Local Errors
    const val INVALID_TABLE_NAME_ERROR = "Table creation error: Invalid table name or table fields not properly defined."
    const val DATABASE_TRANSACTION_ERROR = "Database transaction error: Database thread stopped while transaction was executing."
    const val STALE_DATA_ERROR = "Query results are stale: Please re-run the query or update the database."
    const val INVALID_QUERY_ERROR = "Invalid query parameters: Please use correct data types or properly configure the parameters."

}