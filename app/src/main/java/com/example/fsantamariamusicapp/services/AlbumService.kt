package com.example.fsantamariamusicapp.services

import retrofit2.http.GET
import retrofit2.http.Path
import com.example.fsantamariamusicapp.models.Album

// Interfaz que define los endpoints de la API relacionados con álbumes
interface AlbumService {

    // Obtiene la lista completa de álbumes
    @GET("albums")
    suspend fun getAllAlbums(): List<Album>

    // Obtiene un álbum específico por su ID
    @GET("albums/{id}")
    suspend fun getAlbumById(@Path("id") id: String): Album
}
