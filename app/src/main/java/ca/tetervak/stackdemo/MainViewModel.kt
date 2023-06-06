package ca.tetervak.stackdemo

import androidx.lifecycle.ViewModel
import ca.tetervak.stackdemo.model.StackItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _uiStateFlow: MutableStateFlow<List<StackItem>> =
        MutableStateFlow(emptyList())
    val uiStateFlow: StateFlow<List<StackItem>> =_uiStateFlow

    fun pop(){
        _uiStateFlow.update { list ->
            list.drop(1)
        }
    }

    fun push(value: String){
        _uiStateFlow.update { list ->
            val newItem = StackItem(list.size + 1 , value)
            val newList = list.toMutableList()
            newList.add(0, newItem)
            newList
        }
    }
}