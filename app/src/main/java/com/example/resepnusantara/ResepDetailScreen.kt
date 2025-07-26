package com.aditya.resepnusantara.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aditya.resepnusantara.data.FavoriteManager
import com.aditya.resepnusantara.data.Resep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResepDetailScreen(resep: Resep, onBack: () -> Unit) {
    val context = LocalContext.current
    val favManager = FavoriteManager(context)
    var isFavorite by remember { mutableStateOf(favManager.isFavorite(resep.id)) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(resep.nama) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Text("<")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        favManager.toggleFavorite(resep.id)
                        isFavorite = favManager.isFavorite(resep.id)
                    }) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favorite"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Ganti dengan AsyncImage dari Coil 2.5.0
            AsyncImage(
                model = resep.gambarUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text("Bahan:", style = MaterialTheme.typography.titleMedium)
            Text(resep.bahan)
            Spacer(modifier = Modifier.height(12.dp))
            Text("Langkah-langkah:", style = MaterialTheme.typography.titleMedium)
            Text(resep.langkah)
        }
    }
}
