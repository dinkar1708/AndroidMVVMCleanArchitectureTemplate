package com.example.clean.data.repository.search

import com.example.clean.data.ResultState
import com.example.clean.domain.model.User
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchUsers(): Flow<ResultState<List<User>>>
    suspend fun refreshUsers()
}
