package ca.tetervak.stackdemo.data.repository

import ca.tetervak.stackdemo.data.source.DefaultStackDataSource
import ca.tetervak.stackdemo.domain.StackItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DefaultStackItemRepository(
    private val source: DefaultStackDataSource = DefaultStackDataSource()
) : StackItemRepository {

    // the first item of the list is the top of the stack
    override fun getStackItems(): Flow<List<StackItem>> = source.getItemsFlow().map { list ->
        buildList(list.size) {
            for ((index, value) in list.withIndex().reversed()) {
                add(StackItem(index + 1, value))
            }
        }
    }.flowOn(Dispatchers.Default)

    override suspend fun push(value: String) =
        withContext(Dispatchers.Default) { source.push(value) }

    override suspend fun pop(): String = withContext(Dispatchers.Default) { source.pop() }

}