package com.example.clean.ui.features.users

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clean.domain.model.User
import com.example.clean.ui.components.HorizontalDivider
import com.example.clean.ui.theme.AppLightTheme
import timber.log.Timber

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UsersListView(
    modifier: Modifier, userList: List<User>, onItemClick: (User) -> Unit
) {
    LazyColumn(
        modifier = modifier.background(MaterialTheme.colorScheme.background)
    ) {
        items(userList.size) { index ->
            UserListItem(user = userList[index], onItemClick = onItemClick)
            HorizontalDivider()
        }
    }
}

@Composable
fun UserListItem(user: User, onItemClick: (User) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                Timber.d("User clicked: ${user.login}")
                onItemClick(user)
            }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "User Avatar",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = user.login,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Score: ${user.score}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
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