package com.example.clean.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design system spacing tokens following 4dp/8dp grid system
 * Used by top apps for consistent spacing and rhythm
 */
object AppSpacing {
    // Base spacing unit (4dp increments)
    val space0: Dp = 0.dp
    val space2: Dp = 2.dp
    val space4: Dp = 4.dp
    val space8: Dp = 8.dp
    val space12: Dp = 12.dp
    val space16: Dp = 16.dp
    val space20: Dp = 20.dp
    val space24: Dp = 24.dp
    val space28: Dp = 28.dp
    val space32: Dp = 32.dp
    val space40: Dp = 40.dp
    val space48: Dp = 48.dp
    val space56: Dp = 56.dp
    val space64: Dp = 64.dp
    val space80: Dp = 80.dp
    val space96: Dp = 96.dp
    val space128: Dp = 128.dp

    // Semantic spacing (named by usage)
    val none = space0
    val extraSmall = space4
    val small = space8
    val medium = space16
    val large = space24
    val extraLarge = space32
    val huge = space48

    // Component-specific spacing
    val cardPadding = space16
    val screenPadding = space16
    val listItemPadding = space16
    val iconSize = space24
    val iconSizeLarge = space32
    val buttonHeight = space48
    val appBarHeight = space56
}

/**
 * Elevation tokens for Material 3
 */
object AppElevation {
    val level0: Dp = 0.dp
    val level1: Dp = 1.dp
    val level2: Dp = 3.dp
    val level3: Dp = 6.dp
    val level4: Dp = 8.dp
    val level5: Dp = 12.dp
}

/**
 * Corner radius tokens for consistent shape treatment
 */
object AppCorners {
    val none: Dp = 0.dp
    val extraSmall: Dp = 4.dp
    val small: Dp = 8.dp
    val medium: Dp = 12.dp
    val large: Dp = 16.dp
    val extraLarge: Dp = 24.dp
    val full: Dp = 9999.dp  // Fully rounded
}

/**
 * Border width tokens
 */
object AppBorders {
    val thin: Dp = 1.dp
    val medium: Dp = 2.dp
    val thick: Dp = 4.dp
}

/**
 * Icon size tokens
 */
object AppIconSize {
    val small: Dp = 16.dp
    val medium: Dp = 24.dp
    val large: Dp = 32.dp
    val extraLarge: Dp = 48.dp
    val huge: Dp = 64.dp
}
