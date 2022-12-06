package ca.tetervak.stackdemo.data.repository

import ca.tetervak.stackdemo.data.database.StackItemDao
import ca.tetervak.stackdemo.data.database.StackItemEntity
import ca.tetervak.stackdemo.model.StackItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StackItemRepositoryRoom @Inject constructor(
    private val stackItemDao: StackItemDao
) : StackItemRepository {

    override fun getAllStackItemsFlow(): Flow<List<StackItem>> =
        stackItemDao.getAllStackItemEntitiesFlow().map { entityList ->
            val listSize = entityList.size
            entityList.mapIndexed { index, entity ->
                StackItem(
                    count = listSize - index,
                    value = entity.value
                )
            }
        }

    override suspend fun deleteLastStackItem() {
        stackItemDao.deleteLastStackItemEntity()
    }

    override suspend fun insert(stackItemValue: String) {
        stackItemDao.insert(StackItemEntity(value = stackItemValue))
    }
}