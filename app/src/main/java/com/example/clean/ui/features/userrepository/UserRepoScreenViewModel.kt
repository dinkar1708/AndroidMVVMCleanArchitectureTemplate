package com.example.clean.ui.features.userrepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.data.ResultState
import com.example.clean.di.DefaultDispatcher
import com.example.clean.domain.model.User
import com.example.clean.domain.usecase.UserRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRepoScreenViewModel @Inject constructor(
    private val userRepositoryUseCase: UserRepositoryUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(UserRepoViewState())
    val uiState: StateFlow<UserRepoViewState> = _uiState.asStateFlow()

    fun getUserRepositories(user: User) = viewModelScope.launch(dispatcher) {
        _uiState.value = UserRepoViewState(isLoading = true)

        viewModelScope.launch {

            userRepositoryUseCase.searchUserRepositories(user.login).collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        val repositories = result.data
                        _uiState.value = UserRepoViewState(
                            selectedUser = user,
                            repositories = repositories,
                            isLoading = false
                        )
                    }

                    is ResultState.Error -> {
                        val errorMessage = when {
                            result.error.apiErrorCode != null -> "API Error ${result.error.apiErrorCode.code}: ${result.error.errorMessage}"
                            result.error.localErrorCode != null -> "Local Error ${result.error.localErrorCode.code}: ${result.error.errorMessage}"
                            else -> result.error.errorMessage
                        }
                        _uiState.value = UserRepoViewState(
                            errorMessage = errorMessage,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}
