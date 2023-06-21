package ca.tetervak.stackdemo.data.repository

import ca.tetervak.stackdemo.data.local.ItemDao
import ca.tetervak.stackdemo.domain.StackItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStackItemRepository @Inject constructor(
    private val itemDao: ItemDao
): StackItemRepository {

    override fun getStackItems(): Flow<List<StackItem>> =
        itemDao.getAllItemsFlow().map { list ->
            buildList(list.size) {
                for ((index, value) in list.withIndex()) {
                    add(StackItem(list.size - index, value))
                }
            }
        }.flowOn(Dispatchers.IO)

    override suspend fun push(value: String) =
        withContext(Dispatchers.IO){
            itemDao.insert(value)
        }

    override suspend fun pop(): String =
        withContext(Dispatchers.IO) {
            itemDao.deleteLastItem()
        }
}