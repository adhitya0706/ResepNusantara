package com.aditya.resepnusantara.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Resep::class], version = 1)
abstract class ResepDatabase : RoomDatabase() {
    abstract fun resepDao(): ResepDao

    companion object {
        @Volatile
        private var INSTANCE: ResepDatabase? = null

        fun getInstance(context: Context): ResepDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    ResepDatabase::class.java,
                    "db_resep"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
