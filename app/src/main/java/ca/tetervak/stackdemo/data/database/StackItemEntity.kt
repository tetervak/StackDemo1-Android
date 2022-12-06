package ca.tetervak.stackdemo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stack_items")
data class StackItemEntity(

    @PrimaryKey(autoGenerate = true)
    // using the default column name
    val id: Int = 0,

    @ColumnInfo(name = "item_value")
    val value: String
)
