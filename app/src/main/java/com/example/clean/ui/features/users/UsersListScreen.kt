package com.example.clean.ui.features.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean.domain.model.User
import com.example.clean.ui.components.AppActionBarView
import com.example.clean.ui.components.StateContentBox
import com.example.clean.ui.theme.AppLightTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel,
    onClick: (User) -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()

    UsersListScreenContent(
        isLoading = viewState.isLoading,
        userList = viewState.userList,
        errorMessage = viewState.errorMessage,
        onItemClick = onClick
    )
}

@Composable
fun UsersListScreenContent(
    isLoading: Boolean,
    userList: List<User>,
    errorMessage: String,
    onItemClick: (User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Body with 1/5 of the height
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = "Github Users",
            showBackButton = false
        )

        StateContentBox(
//            modifier = Modifier.weight(4f),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            UsersListView(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                userList = userList,
                onItemClick = onItemClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserListScreenContentPreview() {
    AppLightTheme {
        UsersListScreenContent(
            isLoading = false,
            userList = emptyList(),
            errorMessage = "",
            onItemClick = { }
        )
    }
}