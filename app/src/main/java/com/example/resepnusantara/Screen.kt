package com.aditya.resepnusantara.ui

sealed class Screen(val route: String, val title: String) {
    object Beranda : Screen("beranda", "Beranda")
    object Resep : Screen("resep", "Resep")
    object Panduan : Screen("panduan", "Panduan")
    object Tentang : Screen("tentang", "Tentang")
}
