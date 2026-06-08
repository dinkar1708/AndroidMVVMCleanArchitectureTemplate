package com.example.clean.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import com.example.clean.ui.theme.AppElevation
import com.example.clean.ui.theme.AppLightTheme
import com.example.clean.ui.theme.AppSpacing

/**
 * Modern top app bar component
 * Uses Material 3 design tokens and theme colors
 */
@Composable
fun AppActionBarView(
    modifier: Modifier = Modifier,
    headerText: String,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = AppElevation.level1),
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .height(AppSpacing.appBarHeight)
                .padding(horizontal = AppSpacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            if (showBackButton) {
                IconButton(
                    onClick = onBackClick,
                    modifier = Modifier.size(AppSpacing.buttonHeight)
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Navigate back",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.size(AppSpacing.iconSize)
                    )
                }
            }

            Text(
                text = headerText,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(
                    start = if (showBackButton) AppSpacing.small else AppSpacing.medium
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCommonHeaderPreviewWithAllComponents() {
    AppLightTheme {
        Surface {
            AppActionBarView(modifier = Modifier.fillMaxWidth(),
                headerText = "Text",
                showBackButton = true,
                onBackClick = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenCommonHeaderPreview() {
    AppLightTheme {
        Surface {
            AppActionBarView(modifier = Modifier.fillMaxWidth(),
                headerText = "Text",
                showBackButton = false,
                onBackClick = {})
        }
    }
}