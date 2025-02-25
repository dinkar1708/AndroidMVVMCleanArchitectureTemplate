package com.example.clean.data.repository.search

import com.example.clean.data.CustomError
import com.example.clean.data.LocalErrorCodes
import com.example.clean.data.ResultState
import com.example.clean.data.source.local.dao.UserDao
import com.example.clean.data.source.network.NetworkDataSource
import com.example.clean.data.source.network.toExternal
import com.example.clean.data.source.network.toLocal
import com.example.clean.di.DefaultDispatcher
import com.example.clean.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: UserDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : SearchRepository {

    override suspend fun searchUsers(): Flow<ResultState<List<User>>> = flow {
        try {
            // Refresh data from the network before observing the local data.
            refreshUsers()
            localDataSource.observeAll()
                .map { entities -> entities.toExternal() }
                .collect { users ->
                    if (users.isEmpty()) {
                        emit(
                            ResultState.Error(
                                CustomError(
                                    localErrorCode = LocalErrorCodes.DATA_NOT_FOUND,
                                    errorMessage = "No data found"
                                )
                            )
                        )
                    } else {
                        emit(ResultState.Success(users))
                    }
                }
        } catch (e: Exception) {
            Timber.e(e, "Error fetching users: ${e.message}")
            emit(
                ResultState.Error(
                    CustomError(
                        localErrorCode = LocalErrorCodes.DATA_NOT_FOUND,
                        errorMessage = e.localizedMessage ?: "Unknown error"
                    )
                )
            )
        }
    }

    override suspend fun refreshUsers() {
        withContext(dispatcher) {
            val response = networkDataSource.searchUserList()
            localDataSource.upsertAll(response.users.toLocal())
        }
    }
}
