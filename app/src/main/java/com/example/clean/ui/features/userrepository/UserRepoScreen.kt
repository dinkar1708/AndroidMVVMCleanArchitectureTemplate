package com.example.clean.ui.features.userrepository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean.domain.model.User
import com.example.clean.domain.model.UserRepo
import com.example.clean.ui.components.AppActionBarView
import com.example.clean.ui.components.StateContentBox
import com.example.clean.ui.theme.AppLightTheme

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun UserRepoScreen(
    viewModel: UserRepoScreenViewModel, onBackClick: () -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    UserRepoListScreenContents(
        isLoading = viewState.isLoading,
        selectedUser = viewState.selectedUser,
        repositories = viewState.repositories,
        errorMessage = viewState.errorMessage
    ) { onBackClick() }
}

@Composable
fun UserRepoListScreenContents(
    isLoading: Boolean,
    selectedUser: User,
    repositories: List<UserRepo>,
    errorMessage: String,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        AppActionBarView(
            modifier = Modifier
                .fillMaxWidth(),
            headerText = "${selectedUser.login} Repositories",
            showBackButton = true,
            onBackClick = onBackClick
        )

        // Body with 4/5 of the height
        StateContentBox(
            modifier = Modifier.weight(4f),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            UserRepoListScreen(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(), repositories = repositories, user = selectedUser
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepoListHeaderPreview() {
    val user = User(
        id = 1,
        login = "dinkar1708",
        type = "User",
        url = "url",
        avatarUrl = "avatarUrl",
        score = 1.0
    )

    val repositories = mutableListOf(
        UserRepo(
            id = 1,
            login = "dinkar1708",
            type = "User",
            url = "",
            avatarUrl = "",
        ),
        UserRepo(
            id = 2,
            login = "dinkar1708",
            type = "User",
            url = "",
            avatarUrl = "",
            description = "sdlfjsdf dfjlkdj f"
        )

    )

    AppLightTheme {
        Surface {
            UserRepoListScreenContents(isLoading = false,
                selectedUser = user,
                repositories = repositories,
                errorMessage = "",
                onBackClick = {})
        }
    }
}