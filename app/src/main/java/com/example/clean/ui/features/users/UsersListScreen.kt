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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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

@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
fun UsersListScreen(
    viewModel: UsersListViewModel,
    onClick: (User) -> Unit
) {
    val viewState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    UsersListScreenContent(
        isLoading = viewState.isLoading,
        userList = viewState.userList,
        errorMessage = viewState.errorMessage,
        searchQuery = searchQuery,
        onSearchQueryChanged = viewModel::onSearchQueryChanged,
        onItemClick = onClick
    )
}

@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
fun UsersListScreenContent(
    isLoading: Boolean,
    userList: List<User>,
    errorMessage: String,
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onItemClick: (User) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        // Header
        AppActionBarView(
            modifier = Modifier.fillMaxWidth(),
            headerText = "GitHub Users",
            showBackButton = false
        )

        StateContentBox(
            modifier = Modifier.fillMaxSize(),
            isLoading = isLoading,
            errorMessage = errorMessage
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                // Search bar
                SearchBar(
                    query = searchQuery,
                    onQueryChanged = onSearchQueryChanged
                )

                Spacer(modifier = Modifier.height(AppSpacing.small))

                // App description card (only show when no search)
                if (searchQuery.isBlank()) {
                    AppDescriptionCard()
                    Spacer(modifier = Modifier.height(AppSpacing.small))
                }

                // Users list or empty state
                if (userList.isEmpty() && searchQuery.isNotBlank()) {
                    EmptySearchResults()
                } else {
                    UsersListView(
                        userList = userList,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }
}

/**
 * Modern search bar component
 */
@androidx.compose.material3.ExperimentalMaterial3Api
@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.medium),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Search users by name or type...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { onQueryChanged("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear search",
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            },
            textStyle = MaterialTheme.typography.bodyLarge,
            singleLine = true
        )
    }
}

/**
 * Empty state when no search results found
 */
@Composable
fun EmptySearchResults() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(AppSpacing.large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
        )

        Spacer(modifier = Modifier.height(AppSpacing.medium))

        Text(
            text = "No users found",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(AppSpacing.extraSmall))

        Text(
            text = "Try searching with a different keyword",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Card showing what this app is about with improved contrast
 */
@Composable
fun AppDescriptionCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppSpacing.medium, vertical = AppSpacing.small),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
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
                    modifier = Modifier.size(28.dp)
                )

                Spacer(modifier = Modifier.width(AppSpacing.small))

                Text(
                    text = "What This App Does",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            Text(
                text = "GitHub Users Explorer - A modern Android app showcasing Clean Architecture with MVVM pattern.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(AppSpacing.small))

            Text(
                text = "Browse GitHub users, search by name, and explore their repositories with an elegant Material 3 design.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(AppSpacing.medium))

            // Features row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FeatureChip(
                    icon = Icons.Default.Search,
                    text = "Search Users"
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
 * Feature chip with better contrast
 */
@Composable
fun FeatureChip(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = AppSpacing.medium,
                vertical = AppSpacing.small
            )
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(AppSpacing.extraSmall))

            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@androidx.compose.material3.ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun UserListScreenContentPreview() {
    AppLightTheme {
        UsersListScreenContent(
            isLoading = false,
            userList = emptyList(),
            errorMessage = "",
            searchQuery = "",
            onSearchQueryChanged = { },
            onItemClick = { }
        )
    }
}