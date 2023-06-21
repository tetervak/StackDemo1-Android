package ca.tetervak.stackdemo.domain

import ca.tetervak.stackdemo.data.repository.StackItemRepository
import javax.inject.Inject

class PushToStackUseCase @Inject constructor(
    private val repository: StackItemRepository
) {
    suspend operator fun invoke(value: String) = repository.push(value)
}