package com.example.clean.ui.features.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.clean.data.ResultState
import com.example.clean.di.DefaultDispatcher
import com.example.clean.domain.usecase.SearchRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    private val searchRepositoryUseCase: SearchRepositoryUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableStateFlow(UsersListState())
    val uiState: StateFlow<UsersListState> = _uiState.asStateFlow()

    init {
        loadUsers()
    }

    private fun loadUsers() = viewModelScope.launch(dispatcher) {
        _uiState.value = UsersListState(isLoading = true)

        searchRepositoryUseCase.searchUsers().collectLatest { result ->
            when (result) {
                is ResultState.Success -> {
                    _uiState.value = UsersListState(
                        userList = result.data,
                        isLoading = false
                    )
                }
                is ResultState.Error -> {
                    val errorMessage = when {
                        result.error.apiErrorCode != null ->
                            "API Error ${result.error.apiErrorCode.code}: ${result.error.errorMessage}"
                        result.error.localErrorCode != null ->
                            "Local Error ${result.error.localErrorCode.code}: ${result.error.errorMessage}"
                        else -> result.error.errorMessage
                    }
                    _uiState.value = UsersListState(
                        errorMessage = errorMessage,
                        isLoading = false
                    )
                }
            }
        }
    }

}
