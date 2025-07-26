package com.aditya.resepnusantara.data

import android.content.Context

class FavoriteManager(context: Context) {
    private val prefs = context.getSharedPreferences("favorit", Context.MODE_PRIVATE)

    fun isFavorite(id: Int): Boolean {
        return prefs.getBoolean(id.toString(), false)
    }

    fun toggleFavorite(id: Int) {
        val current = isFavorite(id)
        prefs.edit().putBoolean(id.toString(), !current).apply()
    }
}
