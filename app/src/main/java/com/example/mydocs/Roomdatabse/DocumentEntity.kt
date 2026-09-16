package com.example.mydocs.Roomdatabse


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents_table")
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val category: String,
    val filePath: String,
    val timestamp: Long = System.currentTimeMillis(),
    val isFavorite: Boolean = false
)