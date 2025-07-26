package com.aditya.resepnusantara.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PanduanScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Panduan Aplikasi", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text("➕ Tambah resep melalui tombol tambah.")
        Text("✏️ Edit resep yang sudah ada.")
        Text("🗑️ Hapus jika tidak diperlukan.")
    }
}
