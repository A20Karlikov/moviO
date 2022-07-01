package com.io.moviO.data

sealed class DataResult<out R> {

    data class Success<out T>(val value: T) : DataResult<T>()
    data class Fail(val exception: Exception) : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[value=$value]"
            is Fail -> "Error[exception=$exception]"
        }
    }

    val DataResult<*>.successed get() = this is Success<*> && value != null

}