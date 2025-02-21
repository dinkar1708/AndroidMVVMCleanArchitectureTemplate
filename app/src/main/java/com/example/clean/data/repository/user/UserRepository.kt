package com.example.clean.data.repository.user

import com.example.clean.data.ResultState
import com.example.clean.domain.model.UserRepo
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun searchUserRepositories(login: String): Flow<ResultState<List<UserRepo>>>
    suspend fun refreshUserRepositories(login: String)
}
