package ca.tetervak.stackdemo.ui.stack

import androidx.lifecycle.ViewModel
import ca.tetervak.stackdemo.data.StackDemo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class StackViewModel: ViewModel() {

    private val _stackUiState: MutableStateFlow<StackUiState> =
        MutableStateFlow(StackUiState())
    val stackUiState: StateFlow<StackUiState> = _stackUiState

    private val stack = StackDemo()

    fun pop() {
        _stackUiState.update {
            StackUiState(items = stack.items)
        }
    }

    fun push(value: String){
        stack.push(value)
        _stackUiState.update {
            StackUiState(items = stack.items)
        }
    }
}