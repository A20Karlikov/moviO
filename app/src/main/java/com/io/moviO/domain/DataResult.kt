package com.io.moviO.domain

sealed class DataResult<out R> {

    data class Success<out T>(val value: T) : DataResult<T>()
    data class Fail(val exception: Exception) : DataResult<Nothing>()
}