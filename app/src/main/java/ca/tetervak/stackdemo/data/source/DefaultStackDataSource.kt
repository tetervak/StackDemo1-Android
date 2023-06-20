package ca.tetervak.stackdemo.data.source

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class DefaultStackDataSource {

    // the first item of the list is the bottom of the stack
    private val list: MutableList<String> = mutableListOf()

    private val itemsFlow: MutableSharedFlow<List<String>> = MutableSharedFlow(replay = 1)

    // new copy of the list is emitted to the flow on every stack update
    fun getItemsFlow(): Flow<List<String>> = itemsFlow

    suspend fun push(value: String): Unit = coroutineScope {
        synchronized(list) {
            list.add(value)
            itemsFlow.tryEmit(list.toList())
        }
    }

    suspend fun pop(): String = coroutineScope {
        synchronized(list) {
            val removed = list.removeLast()
            itemsFlow.tryEmit(list.toList())
            removed
        }
    }
}