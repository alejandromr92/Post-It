package com.example.alejandro.postit.utils

import android.util.Log

/**
 * Helper class to register logs.
 */
abstract class LoggerUtils {
    companion object {
       fun logMessage(tag: String, message: String) {
            Log.d(tag, message)
        }

        fun logError(tag: String, message: String, exception: Exception) {
            Log.e(tag, message, exception)
        }
    }
}
