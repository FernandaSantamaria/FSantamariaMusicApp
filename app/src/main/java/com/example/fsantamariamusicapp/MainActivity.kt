package com.example.fsantamariamusicapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.fsantamariamusicapp.ui.theme.FSantamariaMusicAppTheme
import com.example.fsantamariamusicapp.ui.theme.HomeScreenRoute
import com.example.fsantamariamusicapp.ui.theme.AlbumDetailScreenRoute
import com.example.fsantamariamusicapp.screens.HomeScreen
import com.example.fsantamariamusicapp.screens.AlbumDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FSantamariaMusicAppTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = HomeScreenRoute,
                        modifier = Modifier.padding(innerPadding)
                    ){
                        // Ruta del Home
                        composable<HomeScreenRoute> {
                            HomeScreen(navController)
                        }

                        composable<AlbumDetailScreenRoute> { backStack ->
                            val args = backStack.toRoute<AlbumDetailScreenRoute>()
                            AlbumDetailScreen(args.id, navController = navController)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FSantamariaMusicAppTheme {
    }
}