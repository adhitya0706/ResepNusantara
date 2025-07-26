package com.aditya.resepnusantara.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aditya.resepnusantara.R
import com.aditya.resepnusantara.data.Resep
import com.aditya.resepnusantara.data.ResepDatabase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResepListScreen() {
    val context = LocalContext.current
    val dao = remember { ResepDatabase.getInstance(context).resepDao() }
    val scope = rememberCoroutineScope()

    var resepList by remember { mutableStateOf(listOf<Resep>()) }
    var searchQuery by remember { mutableStateOf("") }
    var showForm by remember { mutableStateOf(false) }
    var editingResep by remember { mutableStateOf<Resep?>(null) }
    var confirmDelete by remember { mutableStateOf<Resep?>(null) }

    LaunchedEffect(true) {
        dao.getAll().collect {
            resepList = it
        }
    }

    val filteredList = resepList.filter {
        it.nama.contains(searchQuery, ignoreCase = true) ||
                it.bahan.contains(searchQuery, ignoreCase = true) ||
                it.langkah.contains(searchQuery, ignoreCase = true)
    }

    Surface(color = Color(0xFFF5ECD9), modifier = Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Kedai Kala 🍽️",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color(0xFF3B2F2F)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color(0xFFD7C4A3)
                    )
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        editingResep = null
                        showForm = true
                    },
                    containerColor = Color(0xFF6D4C41)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Tambah")
                }
            }
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                // ✅ Logo
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.kedai_logo),
                        contentDescription = "Logo Kedai Kala",
                        modifier = Modifier
                            .height(100.dp)
                            .padding(4.dp),
                        contentScale = ContentScale.Fit
                    )
                    Text(
                        text = "Selamat Datang di Kedai Kala",
                        color = Color(0xFF6D4C41),
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                // ✅ Input Search
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    label = { Text("Cari Resep...") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize()
                ) {
                    items(filteredList) { resep ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .shadow(6.dp, shape = RoundedCornerShape(16.dp)),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFFFF8E1)
                            ),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                if (resep.gambarUrl.isNotBlank()) {
                                    AsyncImage(
                                        model = resep.gambarUrl,
                                        contentDescription = resep.nama,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(180.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                }

                                Text(
                                    resep.nama,
                                    style = MaterialTheme.typography.titleLarge,
                                    color = Color(0xFF4E342E)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text("📜 Bahan:\n${resep.bahan}", color = Color(0xFF5D4037))
                                Spacer(modifier = Modifier.height(4.dp))
                                Text("🍳 Langkah:\n${resep.langkah}", color = Color(0xFF5D4037))

                                Row(
                                    modifier = Modifier.padding(top = 12.dp),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    IconButton(onClick = {
                                        editingResep = resep
                                        showForm = true
                                    }) {
                                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                                    }
                                    IconButton(onClick = {
                                        confirmDelete = resep
                                    }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Hapus")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (showForm) {
            ResepFormDialog(
                resep = editingResep,
                onDismiss = { showForm = false },
                onSave = { nama, bahan, langkah, gambarUrl ->
                    scope.launch {
                        if (editingResep == null) {
                            dao.insert(
                                Resep(nama = nama, bahan = bahan, langkah = langkah, gambarUrl = gambarUrl)
                            )
                        } else {
                            dao.update(
                                editingResep!!.copy(
                                    nama = nama, bahan = bahan,
                                    langkah = langkah, gambarUrl = gambarUrl
                                )
                            )
                        }
                        showForm = false
                    }
                }
            )
        }

        if (confirmDelete != null) {
            AlertDialog(
                onDismissRequest = { confirmDelete = null },
                title = { Text("Hapus Resep") },
                text = { Text("Yakin ingin menghapus resep ini?") },
                confirmButton = {
                    TextButton(onClick = {
                        scope.launch {
                            dao.delete(confirmDelete!!)
                            confirmDelete = null
                        }
                    }) {
                        Text("Hapus")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { confirmDelete = null }) {
                        Text("Batal")
                    }
                }
            )
        }
    }
}
