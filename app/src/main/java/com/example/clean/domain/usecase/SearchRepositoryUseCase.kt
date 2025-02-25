package com.example.clean.domain.usecase

import com.example.clean.data.ResultState
import com.example.clean.data.repository.search.SearchRepository
import com.example.clean.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRepositoryUseCase @Inject constructor(private val searchRepository: SearchRepository) {

    suspend fun searchUsers(
    ): Flow<ResultState<List<User>>> =
        searchRepository.searchUsers()
}
