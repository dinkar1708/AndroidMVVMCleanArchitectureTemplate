package com.example.clean.ui.features.userrepository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clean.domain.model.User
import com.example.clean.domain.model.UserRepo
import com.example.clean.ui.components.AppChip
import com.example.clean.ui.theme.AppElevation
import com.example.clean.ui.theme.AppLightTheme
import com.example.clean.ui.theme.AppSpacing

/**
 * Modern repository list with card-based design
 * Following Material 3 guidelines and top apps UI patterns
 */
@Composable
fun UserRepoListScreen(
    modifier: Modifier = Modifier,
    repositories: List<UserRepo>,
    user: User
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(AppSpacing.medium),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        items(repositories) { repository ->
            RepositoryListItem(userRepo = repository)
        }
    }
}

/**
 * Modern repository card item with elevated design
 */
@Composable
fun RepositoryListItem(userRepo: UserRepo) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = AppElevation.level1
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.medium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(AppSpacing.small)
            ) {
                Icon(
                    imageVector = Icons.Default.Folder,
                    contentDescription = "Repository",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(AppSpacing.iconSize)
                )

                Text(
                    text = userRepo.login,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                AppChip(
                    text = userRepo.type,
                    selected = false
                )
            }

            if (userRepo.description.isNotEmpty()) {
                Spacer(modifier = Modifier.height(AppSpacing.small))

                Text(
                    text = userRepo.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }

            if (userRepo.url.isNotEmpty()) {
                Spacer(modifier = Modifier.height(AppSpacing.small))

                Text(
                    text = userRepo.url,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRepositoryListPreview() {
    val user = User(
        id = 1,
        login = "dinakr279844",
        type = "User",
        url = "url",
        avatarUrl = "avatarUrl",
        score = 1.0
    )

    val userRepositories = mutableListOf(
        UserRepo(
            id = 1,
            login = "dinkar1708",
            type = "User",
            url = "",
            avatarUrl = "",
            description = "sdlfjsdf dfjlkdj f"
        )
    )

    AppLightTheme {
        UserRepoListScreen(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            repositories = userRepositories,
            user = user
        )
    }
}