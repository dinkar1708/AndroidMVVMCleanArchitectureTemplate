package com.example.clean.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * State management container with loading, error, and content states
 * Uses modern Material 3 design for loading and error states
 */
@Composable
fun StateContentBox(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    errorMessage: String,
    onRetry: (() -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Box(modifier) {
        when {
            isLoading -> {
                AppLoadingIndicator()
            }

            errorMessage.isNotEmpty() -> {
                AppErrorState(
                    message = errorMessage,
                    onRetry = onRetry
                )
            }

            else -> {
                content()
            }
        }
    }
}
