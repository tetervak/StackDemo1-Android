package ca.tetervak.stackdemo.domain

import ca.tetervak.stackdemo.data.repository.StackItemRepository
import javax.inject.Inject

class PopStackUseCase @Inject constructor(
    private val repository: StackItemRepository
) {
    suspend operator fun invoke(): String = repository.pop()
}