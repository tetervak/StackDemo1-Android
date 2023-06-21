package ca.tetervak.stackdemo.ui.stack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.stackdemo.domain.GetStackItemsUseCase
import ca.tetervak.stackdemo.domain.PopStackUseCase
import ca.tetervak.stackdemo.domain.PushToStackUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StackViewModel @Inject constructor(
    getStackItemsUseCase: GetStackItemsUseCase,
    private val pushToStackUseCase: PushToStackUseCase,
    private val popStackUseCase: PopStackUseCase
) : ViewModel() {

    val stackUiState: StateFlow<StackUiState> =
        getStackItemsUseCase().map { StackUiState(items = it) }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StackUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun pop() = viewModelScope.launch {
        popStackUseCase()
    }

    fun push(value: String) = viewModelScope.launch {
        pushToStackUseCase(value)
    }
}