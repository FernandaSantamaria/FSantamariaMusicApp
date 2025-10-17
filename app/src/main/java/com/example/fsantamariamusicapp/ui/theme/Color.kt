package com.example.fsantamariamusicapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Tonos base
val NeonGreen = Color(0xFF00FFB3)
val ElectricCyan = Color(0xFF00FFFF)
val DeepTeal = Color(0xFF043F40)
val MidnightTeal = Color(0xFF021A1B)
val PurpleNeon = Color(0xFFB200FF)
val DeepPurple = Color(0xFF4A0072)
val MagentaAccent = Color(0xFFFF2EAA)


val BackGroundGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF10002B),   // Púrpura muy oscuro
        Color(0xFF004C4C),   // Verde azulado intermedio
        Color(0xFF00BFA6)    // Turquesa brillante
    )
)

// Gradiente para imágenes
val BackGroundImageGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF6A00FF),   // Púrpura intenso
        Color(0xFF00BFA6)    // Verde agua
    )
)

// Botón Play – mezcla entre neón verde y violeta
val ButtonPlayGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF00FFB3),   // Verde neón
        Color(0xFF9D00FF)    // Violeta brillante
    )
)

// Tarjetas con sombras frías y matices púrpura
val RepCard = Color(0xFF0A2F32)
val BackGroundCard = Color(0xFF122A3A)
val AlbumDetailColor = Color(0xFF241E33)

// Texto y acentos
val AlbumTitleColor = NeonGreen
val SubtitleColor = Color(0xFFC9BFFF)  // Texto claro con tinte violeta
val AccentColor = PurpleNeon
