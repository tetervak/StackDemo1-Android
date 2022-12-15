package ca.tetervak.stackdemo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.tetervak.stackdemo.data.repository.StackItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stackItemRepository: StackItemRepository
): ViewModel() {

    val stackItemsFlow = stackItemRepository.getAllStackItemsFlow()

    fun pop(){
        viewModelScope.launch(Dispatchers.IO){
            stackItemRepository.deleteLastStackItem()
        }
    }

    fun push(value: String){
        viewModelScope.launch(Dispatchers.IO){
            stackItemRepository.insert(stackItemValue = value)
        }
    }
}