package ca.tetervak.stackdemo.domain

import ca.tetervak.stackdemo.data.repository.StackItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStackItemsUseCase @Inject constructor(
    private val repository: StackItemRepository
) {
    operator fun invoke(): Flow<List<StackItem>> = repository.getStackItems()
}