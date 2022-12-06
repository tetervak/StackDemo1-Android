package ca.tetervak.stackdemo.data.repository

import ca.tetervak.stackdemo.model.StackItem
import kotlinx.coroutines.flow.Flow

interface StackItemRepository {

    fun getAllStackItemsFlow(): Flow<List<StackItem>>

    suspend fun deleteLastStackItem()

    suspend fun insert(stackItemValue: String)
}