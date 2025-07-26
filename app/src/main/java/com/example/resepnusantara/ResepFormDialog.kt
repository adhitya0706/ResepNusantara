package com.aditya.resepnusantara.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aditya.resepnusantara.data.Resep

@Composable
fun ResepFormDialog(
    resep: Resep? = null,
    onDismiss: () -> Unit,
    onSave: (nama: String, bahan: String, langkah: String, gambarUrl: String) -> Unit
) {
    var nama by remember { mutableStateOf(resep?.nama ?: "") }
    var bahan by remember { mutableStateOf(resep?.bahan ?: "") }
    var langkah by remember { mutableStateOf(resep?.langkah ?: "") }
    var gambarUrl by remember { mutableStateOf(resep?.gambarUrl ?: "") }

    // Launcher untuk memilih gambar dari galeri
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        Log.d("GAMBAR_URI", "Launcher dipanggil") // 🟢 Tambahkan baris ini
        uri?.let {
            gambarUrl = it.toString()
            Log.d("GAMBAR_URI", "Dipilih: $gambarUrl") // 🟢 Log ini akan muncul di Logcat
        } ?: Log.d("GAMBAR_URI", "Tidak ada gambar yang dipilih")
    }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (resep == null) "Tambah Resep" else "Edit Resep") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nama,
                    onValueChange = { nama = it },
                    label = { Text("Nama Resep") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = bahan,
                    onValueChange = { bahan = it },
                    label = { Text("Bahan") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = langkah,
                    onValueChange = { langkah = it },
                    label = { Text("Langkah") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = gambarUrl,
                    onValueChange = { gambarUrl = it },
                    label = { Text("URL / Path Gambar") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { launcher.launch("image/*") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("📷 Pilih Gambar dari Galeri")
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (nama.isNotBlank()) {
                    onSave(nama, bahan, langkah, gambarUrl)
                }
            }) {
                Text("Simpan")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Batal")
            }
        }
    )
}
