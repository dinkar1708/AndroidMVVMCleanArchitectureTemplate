package com.example.clean.domain.usecase


import com.example.clean.data.ResultState
import com.example.clean.data.repository.user.UserRepository
import com.example.clean.domain.model.UserRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun searchUserRepositories(login: String): Flow<ResultState<List<UserRepo>>> {
        return userRepository.searchUserRepositories(login)
    }
}
