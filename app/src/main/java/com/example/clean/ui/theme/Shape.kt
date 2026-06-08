package com.example.clean.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * Material 3 Shape System
 * Defines corner radius for different component sizes
 * Following top apps design patterns
 */
val AppShapes = Shapes(
    // Extra small components (chips, small buttons)
    extraSmall = RoundedCornerShape(4.dp),

    // Small components (buttons, text fields)
    small = RoundedCornerShape(8.dp),

    // Medium components (cards, dialogs)
    medium = RoundedCornerShape(12.dp),

    // Large components (bottom sheets, large cards)
    large = RoundedCornerShape(16.dp),

    // Extra large components (full-screen dialogs)
    extraLarge = RoundedCornerShape(28.dp)
)
