package com.example.lolstatistic.network

class ApiResponse<T>(
    val data: T?,
    val error: Throwable?
)