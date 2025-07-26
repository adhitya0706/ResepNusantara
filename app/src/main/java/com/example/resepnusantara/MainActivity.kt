package com.aditya.resepnusantara

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.aditya.resepnusantara.theme.AppTheme
import com.aditya.resepnusantara.ui.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val navController = rememberNavController()
                MainScreen(navController)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    val items = listOf(
        Screen.Beranda to Icons.Default.Home,
        Screen.Resep to Icons.Default.ListAlt, // GANTI agar tidak warning
        Screen.Panduan to Icons.Filled.Menu,   // GANTI agar tidak error
        Screen.Tentang to Icons.Default.Info
    )

    var currentRoute by remember { mutableStateOf(Screen.Beranda.route) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { (screen, icon) ->
                    NavigationBarItem(
                        selected = currentRoute == screen.route,
                        onClick = {
                            currentRoute = screen.route
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(icon, contentDescription = screen.title) },
                        label = { Text(screen.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Beranda.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Beranda.route) { BerandaScreen() }
            composable(Screen.Resep.route) { ResepListScreen() }
            composable(Screen.Panduan.route) { PanduanScreen() }
            composable(Screen.Tentang.route) { TentangScreen() }
        }
    }
}
