package com.example.client_app.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HexCodeDao {
    @Query("SELECT * FROM hex_codes ORDER BY timestamp DESC")
    fun getAllCodes(): Flow<List<HexCode>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(hexCode: HexCode)

    @Query("DELETE FROM hex_codes WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("DELETE FROM hex_codes")
    suspend fun deleteAll()
}
