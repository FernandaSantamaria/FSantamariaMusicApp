package com.example.fsantamariamusicapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//  Tema oscuro principal: morado + turquesa + neón
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00FFB3),           // Verde neón
    secondary = Color(0xFF9D00FF),         // Púrpura brillante
    tertiary = Color(0xFF00E5FF),          // Azul cian eléctrico
    background = Color(0xFF0B0C10),        // Negro azulado profundo
    surface = Color(0xFF121B22),           // Fondo más claro para tarjetas
    onPrimary = Color(0xFF000000),         // Negro sobre verde neón
    onSecondary = Color.White,             // Texto blanco sobre púrpura
    onTertiary = Color(0xFF000000),
    onBackground = Color(0xFFE6F8F7),      // Blanco verdoso para texto
    onSurface = Color(0xFFE0E0E0)          // Gris claro
)

// Tema claro con matices suaves
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00D9A0),           // Verde agua
    secondary = Color(0xFFB388FF),         // Lavanda suave
    tertiary = Color(0xFF00BFA6),          // Turquesa medio
    background = Color(0xFFF3F3F8),
    surface = Color(0xFFFFFFFF),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1B1B1F),
    onSurface = Color(0xFF1B1B1F)
)

@Composable
fun FSantamariaMusicAppTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
