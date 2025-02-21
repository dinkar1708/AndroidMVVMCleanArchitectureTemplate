package com.example.clean.data.repository.user

import com.example.clean.data.CustomError
import com.example.clean.data.LocalErrorCodes
import com.example.clean.data.ResultState
import com.example.clean.data.source.local.dao.UserRepoDao
import com.example.clean.data.source.network.NetworkDataSource
import com.example.clean.data.source.network.toExternal
import com.example.clean.data.source.network.toLocal
import com.example.clean.di.DefaultDispatcher
import com.example.clean.domain.model.UserRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: UserRepoDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun searchUserRepositories(login: String): Flow<ResultState<List<UserRepo>>> {
        return flow {
            try {
                refreshUserRepositories(login)
                localDataSource.observeAll()
                    .map { entities -> entities.toExternal() }
                    .collect { repositories ->
                        emit(ResultState.Error(CustomError(localErrorCode = LocalErrorCodes.DATA_NOT_FOUND, errorMessage = "e.localizedMessage")))
//                        emit(ResultState.Success(repositories))
                    }

            } catch (e: Exception) {
                Timber.e(e, "Error fetching user repositories ${e.message}")
                emit(ResultState.Error(CustomError(localErrorCode = LocalErrorCodes.DATA_NOT_FOUND, errorMessage = e.localizedMessage)))
            }
        }
    }


    override suspend fun refreshUserRepositories(login: String) {
        Timber.d("refreshUserRepositories....$login")
        withContext(dispatcher) {
            networkDataSource.refreshUserRepositories(login).userRepositories.toLocal().let { localRepos ->
                Timber.d("Got save data $localRepos")
                localDataSource.upsertAll(localRepos)
            }
        }
    }
}