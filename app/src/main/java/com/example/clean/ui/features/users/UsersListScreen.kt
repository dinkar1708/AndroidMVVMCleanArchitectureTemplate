package com.example.clean.ui.features.users

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.clean.domain.model.User
import com.example.clean.ui.components.AppActionBarView
import com.example.clean.ui.components.StateContentBox
import com.example.clean.ui.theme.AppLightTheme
import com.example.clean.ui.theme.AppSpacing

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
            modifier = Modifier.fillMaxSize(),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // App description card
                AppDescriptionCard()

                Spacer(modifier = Modifier.height(AppSpacing.small))

                // Users list
                UsersListView(
                    userList = userList,
                    onItemClick = onItemClick
                )
            }
        }
    }
}

/**
 * Card showing what this app is about
 */
@Composable
fun AppDescriptionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.medium, vertical = AppSpacing.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(AppSpacing.medium)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(AppSpacing.small))

                Text(
                    text = "About This App",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(AppSpacing.small))

            Text(
                text = "GitHub Explorer is a modern Android app demonstrating Clean Architecture with MVVM pattern. Browse GitHub users and explore their repositories.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            // Features row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureChip(
                    icon = Icons.Default.Search,
                    text = "Browse Users"
                )
                FeatureChip(
                    icon = Icons.Default.Code,
                    text = "View Repos"
                )
            }
        }
    }
}

/**
 * Small feature chip
 */
@Composable
fun FeatureChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(AppSpacing.extraSmall)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(16.dp)
        )

        Spacer(modifier = Modifier.width(AppSpacing.extraSmall))

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            fontWeight = FontWeight.Medium
        )
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