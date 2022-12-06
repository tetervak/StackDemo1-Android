package ca.tetervak.stackdemo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StackItemEntity::class], version = 1, exportSchema = false)
abstract class StackDatabase : RoomDatabase() {

    abstract fun stackItemDao(): StackItemDao
}
