package com.example.clean.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * Minimal Color Palette (5-7 colors total)
 * Following top apps minimalist design approach
 */

// Core Brand Color
val Primary = Color(0xFF0066FF)           // Vibrant blue - main brand color

// Accent Colors
val Accent = Color(0xFF10B981)            // Teal/Green - for success and accents
val Error = Color(0xFFEF4444)             // Red - for errors and warnings

// Neutral Colors - Light Theme
val Surface = Color(0xFFFFFFFF)           // White - cards and surfaces
val Background = Color(0xFFF8FAFC)        // Light gray - screen background
val TextPrimary = Color(0xFF0F172A)       // Dark slate - primary text
val TextSecondary = Color(0xFF64748B)     // Gray - secondary text
val Divider = Color(0xFFE2E8F0)           // Light gray - borders and dividers

// Dark Theme - Neutral Colors
val BackgroundDark = Color(0xFF0F172A)    // Dark slate - screen background
val SurfaceDark = Color(0xFF1E293B)       // Lighter dark - cards and surfaces
val TextPrimaryDark = Color(0xFFF8FAFC)   // Light - primary text
val TextSecondaryDark = Color(0xFF94A3B8) // Medium gray - secondary text
val DividerDark = Color(0xFF334155)       // Dark gray - borders and dividers

// Helper colors (derived from main colors)
val PrimaryLight = Color(0xFFE6F2FF)      // Light blue container
val AccentLight = Color(0xFFD1FAE5)       // Light green container
val ErrorLight = Color(0xFFFEE2E2)        // Light red container

// Universal colors
val White = Color(0xFFFFFFFF)
val Black = Color(0xFF000000)
val Scrim = Color(0x80000000)             // Semi-transparent overlay