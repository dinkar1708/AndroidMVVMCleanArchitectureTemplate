package com.example.clean.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Dark color scheme - minimal palette
 */
private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = Primary,
    onPrimaryContainer = White,

    secondary = Accent,
    onSecondary = White,
    secondaryContainer = Accent,
    onSecondaryContainer = White,

    tertiary = Accent,
    onTertiary = White,
    tertiaryContainer = AccentLight,
    onTertiaryContainer = Accent,

    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Error,

    background = BackgroundDark,
    onBackground = TextPrimaryDark,

    surface = SurfaceDark,
    onSurface = TextPrimaryDark,
    surfaceVariant = SurfaceDark,
    onSurfaceVariant = TextSecondaryDark,

    outline = DividerDark,
    outlineVariant = DividerDark,

    scrim = Scrim,
    inverseSurface = Surface,
    inverseOnSurface = TextPrimary,
    inversePrimary = PrimaryLight,
    surfaceTint = Primary
)

/**
 * Light color scheme - minimal palette
 */
private val LightColorScheme = lightColorScheme(
    primary = Primary,
    onPrimary = White,
    primaryContainer = PrimaryLight,
    onPrimaryContainer = Primary,

    secondary = Accent,
    onSecondary = White,
    secondaryContainer = AccentLight,
    onSecondaryContainer = Accent,

    tertiary = Accent,
    onTertiary = White,
    tertiaryContainer = AccentLight,
    onTertiaryContainer = Accent,

    error = Error,
    onError = White,
    errorContainer = ErrorLight,
    onErrorContainer = Error,

    background = Background,
    onBackground = TextPrimary,

    surface = Surface,
    onSurface = TextPrimary,
    surfaceVariant = Surface,
    onSurfaceVariant = TextSecondary,

    outline = Divider,
    outlineVariant = Divider,

    scrim = Scrim,
    inverseSurface = SurfaceDark,
    inverseOnSurface = TextPrimaryDark,
    inversePrimary = PrimaryLight,
    surfaceTint = Primary
)

/**
 * Main theme composable for the app
 * Supports light/dark themes and Material You dynamic colors
 *
 * @param darkTheme Whether to use dark theme
 * @param dynamicColor Whether to use Android 12+ dynamic colors (Material You)
 * @param content The composable content to wrap with theme
 */
@Composable
fun AppLightTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()

            // Make status bar icons dark/light based on theme
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = AppShapes,
        content = content
    )
}