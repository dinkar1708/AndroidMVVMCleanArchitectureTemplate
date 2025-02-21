package com.example.clean.domain.usecase


import com.example.clean.data.ApiErrorCodes
import com.example.clean.data.CustomError
import com.example.clean.data.ResultState
import com.example.clean.data.repository.user.UserRepository
import com.example.clean.domain.model.UserRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend fun searchUserRepositories(
        login: String,
    ): Flow<ResultState<List<UserRepo>>> {
        return userRepository.searchUserRepositories(login)
            .map { ResultState.Success(it) as ResultState<List<UserRepo>> }
            .catch { exception ->
                emit(ResultState.Error(CustomError(apiErrorCode = ApiErrorCodes.UNKNOWN_ERROR, errorMessage = exception.localizedMessage ?: "Unknown error")))
            }
    }
}

