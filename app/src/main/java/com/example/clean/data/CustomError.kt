package com.example.clean.data

data class CustomError(val apiErrorCode: ApiErrorCodes? = null, val localErrorCode: LocalErrorCodes? = null, val errorMessage: String)
