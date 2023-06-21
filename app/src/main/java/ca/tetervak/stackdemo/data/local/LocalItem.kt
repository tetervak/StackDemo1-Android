package ca.tetervak.stackdemo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class LocalItem(
    // using the default column names
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: String
)