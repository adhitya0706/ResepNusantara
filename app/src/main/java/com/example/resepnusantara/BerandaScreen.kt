package com.aditya.resepnusantara.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aditya.resepnusantara.R

@Composable
fun BerandaScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8E1)) // Warna krem
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // ✅ Logo Kedai
        Image(
            painter = painterResource(id = R.drawable.kedai_logo), // Pastikan file ini ada di drawable
            contentDescription = "Logo Kedai",
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ✅ Judul
        Text(
            text = "Selamat Datang di Resep Nusantara 👋",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4E342E),
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ Deskripsi
        Text(
            text = "Jelajahi berbagai resep lezat khas Nusantara dari Timur Indonesia. Tambahkan, simpan, dan masak dengan mudah dari aplikasi ini.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF5D4037),
            lineHeight = 22.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // ✅ Kartu informasi
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE6D3B3)
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    "Fitur Aplikasi:",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF4E342E)
                )
                Spacer(Modifier.height(8.dp))
                Text("• Tambah & edit resep secara mudah")
                Text("• Simpan resep lengkap dengan gambar")
                Text("• Panduan penggunaan & info aplikasi")
            }
        }
    }
}
