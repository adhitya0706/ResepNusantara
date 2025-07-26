package com.aditya.resepnusantara.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resep")
data class Resep(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nama: String,
    val bahan: String,
    val langkah: String,
    val gambarUrl: String = "" // ✅ properti tambahan untuk URL gambar
)
