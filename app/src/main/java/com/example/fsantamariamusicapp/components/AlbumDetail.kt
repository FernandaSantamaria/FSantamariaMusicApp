package com.example.fsantamariamusicapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause  // CAMBIADO: Pause en lugar de ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.fsantamariamusicapp.models.Album
import com.example.fsantamariamusicapp.ui.theme.AlbumTitleColor
import com.example.fsantamariamusicapp.ui.theme.BackGroundCard
import androidx.compose.material.icons.outlined.Repeat



@Composable
fun AlbumDetail(
    album: Album?,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black)
    ) {
        // Imagen del álbum
        AsyncImage(
            model = album?.image ?: "",
            contentDescription = album?.title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .align(Alignment.TopEnd),
            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(30.dp)
                    .background(Color.Black.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "favorite",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }


        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(15.dp)
        ) {
            Text(
                text = album?.title ?: "Título no disponible",
                color = AlbumTitleColor,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 4.dp, start = 8.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp
            )

            Text(
                text = album?.artist ?: "Artista no disponible",
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // 🎛 Controles inferiores: Play, Pause y Repeat
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Grupo central: Play y Pause
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Botón Play
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.7f))
                            .clickable { /* acción de play */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "play",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    // Botón Pause
                    Box(
                        modifier = Modifier
                            .size(55.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.7f))
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Pause,
                            contentDescription = "pause",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                // Botón Repeat (alineado a la derecha)
                Box(
                    modifier = Modifier
                        .size(55.dp)
                        .clip(CircleShape)
                        .background(Color.Black.copy(alpha = 0.7f))
                        .clickable { /* acción de repeat */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Repeat,
                        contentDescription = "repeat",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        }
    }
}
