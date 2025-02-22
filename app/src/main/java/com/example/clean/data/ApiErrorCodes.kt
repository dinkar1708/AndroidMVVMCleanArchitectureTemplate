package com.example.clean.data

enum class ApiErrorCodes(val code: Int) {
    UNKNOWN_ERROR(500),
    NOT_FOUND(404),
    NETWORK_FAILURE(503),
    UNAUTHORIZED(401),
    BAD_REQUEST(400)
}