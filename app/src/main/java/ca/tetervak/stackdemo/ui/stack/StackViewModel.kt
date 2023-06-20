package ca.tetervak.stackdemo.ui.stack

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.stackdemo.data.repository.DefaultStackItemRepository
import ca.tetervak.stackdemo.data.repository.StackItemRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StackViewModel(
    private val repository: StackItemRepository = DefaultStackItemRepository()
): ViewModel() {

    private val _inputUiState: MutableState<String> = mutableStateOf("")
    val inputUiState: State<String> = _inputUiState

    fun setInput(value: String) {
        _inputUiState.value = value
    }

    val stackUiState: StateFlow<StackUiState> =
        repository.getStackItems().map { StackUiState(items = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = StackUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    fun pop() = viewModelScope.launch {
            val removed = repository.pop()
            _inputUiState.value = removed
        }

    fun push() = viewModelScope.launch {
        repository.push(inputUiState.value)
    }
}