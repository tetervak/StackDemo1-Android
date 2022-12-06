package ca.tetervak.stackdemo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface StackItemDao {

    @Query("SELECT * FROM stack_items ORDER BY id DESC ")
    fun getAllStackItemEntitiesFlow(): Flow<List<StackItemEntity>>

    @Query("SELECT MAX(id) FROM stack_items")
    suspend fun getMaxId(): Int

    @Query("DELETE FROM stack_items WHERE id=:id")
    suspend fun deleteItemEntityById(id: Int)

    @Transaction
    suspend fun deleteLastStackItemEntity(){
        deleteItemEntityById(getMaxId())
    }

    @Insert
    suspend fun insert(stackItemEntity: StackItemEntity)
}