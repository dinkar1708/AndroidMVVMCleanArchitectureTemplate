package com.example.clean.ui.features.users

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clean.domain.model.User
import com.example.clean.ui.theme.AppElevation
import com.example.clean.ui.theme.AppLightTheme
import com.example.clean.ui.theme.AppSpacing
import timber.log.Timber

/**
 * Modern users list with card-based design
 * Following Material 3 guidelines and top apps UI patterns
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersListView(
    modifier: Modifier = Modifier,
    userList: List<User>,
    onItemClick: (User) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentPadding = PaddingValues(AppSpacing.medium),
        verticalArrangement = Arrangement.spacedBy(AppSpacing.medium)
    ) {
        items(userList.size) { index ->
            UserListItem(
                user = userList[index],
                onItemClick = onItemClick
            )
        }
    }
}

/**
 * Modern user card item with elevated design
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListItem(user: User, onItemClick: (User) -> Unit) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            Timber.d("User clicked: ${user.login}")
            onItemClick(user)
        },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = AppElevation.level1,
            pressedElevation = AppElevation.level2
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppSpacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar with circular background
            Surface(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "User Avatar",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(AppSpacing.small)
                )
            }

            Spacer(modifier = Modifier.width(AppSpacing.medium))

            // User info
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = user.login,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(AppSpacing.extraSmall))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(AppSpacing.small)
                ) {
                    Text(
                        text = user.type ?: "User",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    if (user.score != null) {
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = "Score: ${user.score}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    val users = mutableListOf(
        User(
            id = 1,
            login = "dinkar1708",
            type = "User",
            avatarUrl = "uavatarUrlavatarUrlavatarUrlavatarUrlrl",
        ), User(
            id = 2,
            login = "dinkar170811",
            type = "User",
            avatarUrl = "avatarUrlavatarUrlavatarUrlavatarUrl",
        )
    )

    AppLightTheme {
        UsersListView(
            modifier = Modifier
                .fillMaxWidth(),
            userList = users,
        ) {}
    }
}