package ca.tetervak.stackdemo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Query("SELECT (value) FROM items ORDER BY id DESC ")
    fun getAllItemsFlow(): Flow<List<String>>

    @Query("SELECT MAX(id) FROM items")
    suspend fun getMaxId(): Int

    @Query("DELETE FROM items WHERE id=:id")
    suspend fun deleteItemById(id: Int)

    @Query("SELECT (value) FROM items WHERE id=:id")
    suspend fun getItemById(id: Int): String

    @Transaction
    suspend fun deleteLastItem(): String{
        val maxId  = getMaxId()
        val removed = getItemById(maxId)
        deleteItemById(maxId)
        return removed
    }

    @Insert
    suspend fun insert(item: LocalItem)

    @Query("INSERT INTO items (value) VALUES (:value)")
    suspend fun insert(value: String)
}