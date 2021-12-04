package com.example.core.test.util

class TestingException(message: String = GENERIC_EXCEPTION_MESSAGE) : Throwable(message) {
    companion object {
        const val GENERIC_EXCEPTION_MESSAGE = "Something error came while executing"
    }
}