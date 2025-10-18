package com.example.fsantamariamusicapp.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import androidx.navigation.NavController
import com.example.fsantamariamusicapp.models.Album
import com.example.fsantamariamusicapp.services.AlbumService
import com.example.fsantamariamusicapp.ui.theme.BackGroundGradient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.text.font.FontWeight
import com.example.fsantamariamusicapp.components.AlbumCardBox
import com.example.fsantamariamusicapp.components.AlbumCardRow
import com.example.fsantamariamusicapp.components.Header
import com.example.fsantamariamusicapp.components.RepCard
import com.example.fsantamariamusicapp.ui.theme.ButtonPlayGradient
import com.example.fsantamariamusicapp.ui.theme.AlbumDetailScreenRoute
@Composable
fun HomeScreen(
    navController: NavController
){
    // Estado para almacenar la lista de álbumes obtenida desde la API
    var albums by remember {
        mutableStateOf(listOf<Album>())
    }

    // Estado para controlar la animación de carga
    var loading by remember {
        mutableStateOf(true)
    }

    // Efecto que se ejecuta una sola vez para consumir la API
    LaunchedEffect(true) {
        try {
            val retrofit = Retrofit
                .Builder()
                .baseUrl("https://music.juanfrausto.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(AlbumService::class.java)
            val result = async(Dispatchers.IO) {
                service.getAllAlbums()
            }

            Log.i("HomeScreen", "${result.await()}")
            albums = result.await() // Guardar los álbumes en el estado
            loading = false
        } catch (e: Exception) {
            loading = false
            Log.e("HomeScreen", e.toString())
        }
    }

    // Mostrar animación de carga mientras se obtienen los datos
    if (loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGroundGradient), // Fondo de la pantalla
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = Color(0xFFC8DC50) // Color principal de la app
                )
                Text(
                    text = "Loading albums...",
                    color = Color.White,
                    modifier = Modifier.padding(top = 16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    } else {
        // Layout principal de la pantalla
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackGroundGradient)
                .padding(top = 40.dp, start = 15.dp, end = 15.dp, bottom = 20.dp)
        ) {
            // Header de la pantalla con estilo
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(ButtonPlayGradient)
            ) {
                Header() // Componente Header
            }

            // Sección de álbumes destacados y recientemente reproducidos
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(horizontal = 5.dp)
            ) {
                // Títulos y botón "See more"
                Row(
                    modifier = Modifier.padding(top = 15.dp)
                ) {
                    Text(
                        text = "Albums",
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "See more",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                // Lista horizontal de álbumes en tarjetas
                LazyRow(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 7.dp, bottom = 10.dp)
                ) {
                    items(albums) { album ->
                        AlbumCardBox(
                            album = album,
                            onClick = {
                                navController.navigate(AlbumDetailScreenRoute(album.id))
                            }
                        )
                    }
                }

                // Títulos de la sección "Recently Played"
                Row {
                    Text(
                        text = "Recently Played",
                        modifier = Modifier.weight(1f),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "See more",
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }

            // Lista vertical de álbumes y reproductor fijo
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(6f)
            ) {
                Column(
                    modifier = Modifier.padding(top = 10.dp, start = 5.dp, end = 5.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(albums) { album ->
                            AlbumCardRow(
                                album = album,
                                onClick = {
                                    navController.navigate(AlbumDetailScreenRoute(album.id))
                                }
                            )
                        }
                    }
                }

                // Reproductor fijo en la parte inferior si hay álbumes
                if (albums.isNotEmpty()) {
                    Box(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ){
                        RepCard(albums[0])
                    }
                }
            }
        }
    }
}
