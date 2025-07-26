package com.aditya.resepnusantara.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TentangScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tentang Aplikasi", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("📌 Dibuat oleh: Aditya")
        Text("📌 Versi: 1.0")
        Text("📌 Teknologi: Jetpack Compose, Room, Coil")
    }
}
