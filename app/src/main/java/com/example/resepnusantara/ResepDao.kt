package com.aditya.resepnusantara.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ResepDao {
    @Query("SELECT * FROM resep ORDER BY id DESC")
    fun getAll(): Flow<List<Resep>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(resep: Resep)

    @Update
    suspend fun update(resep: Resep)

    @Delete
    suspend fun delete(resep: Resep)
}
