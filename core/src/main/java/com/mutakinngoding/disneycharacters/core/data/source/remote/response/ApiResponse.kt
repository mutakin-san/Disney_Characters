package com.mutakinngoding.disneycharacters.core.data.source.remote.response

sealed class ApiResponse<out R> {
    object Empty : ApiResponse<Nothing>()
    class Error(val errorMessage: String) : ApiResponse<Nothing>()
    class Success<T>(val data: T) : ApiResponse<T>()
}