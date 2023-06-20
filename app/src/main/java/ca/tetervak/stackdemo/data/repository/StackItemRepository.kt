package ca.tetervak.stackdemo.data.repository

import ca.tetervak.stackdemo.domain.StackItem
import kotlinx.coroutines.flow.Flow

interface StackItemRepository {

    // the first item of the list is the top of the stack
    fun getStackItems(): Flow<List<StackItem>>

    suspend fun push(value: String)

    suspend fun pop(): String
}