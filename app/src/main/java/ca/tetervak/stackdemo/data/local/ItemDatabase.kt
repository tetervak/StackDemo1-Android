package ca.tetervak.stackdemo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalItem::class], version = 2, exportSchema = false
)
abstract class ItemDatabase: RoomDatabase() {
    abstract val itemDao: ItemDao
}