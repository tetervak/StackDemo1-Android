package ca.tetervak.stackdemo

import androidx.lifecycle.ViewModel
import ca.tetervak.stackdemo.model.StackItem
import kotlinx.coroutines.flow.flow

class MainViewModel: ViewModel() {

    val stackItemsFlow = flow {
        val list = buildList<StackItem> {
            for (i in 5 downTo 1){
                add(StackItem(i, "item $i"))
            }
        }
        emit(list)
    }

    fun pop(){

    }

    fun push(value: String){

    }

}