package com.example.client_app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hex_codes")
data class HexCode(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val code: String,
    val timestamp: Long = System.currentTimeMillis()
)
