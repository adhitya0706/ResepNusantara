package com.aditya.resepnusantara.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val KalaLightColorScheme = lightColorScheme(
    primary = Color(0xFF5D4037),      // Coklat tua
    onPrimary = Color.White,
    secondary = Color(0xFFD7CCC8),    // Krem abu lembut
    onSecondary = Color(0xFF3E2723),
    background = Color(0xFFFFF8E1),   // Krem terang
    onBackground = Color(0xFF3E2723),
    surface = Color(0xFFFFF3E0),      // Putih-krem
    onSurface = Color(0xFF3E2723),
)

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val colorScheme = KalaLightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
