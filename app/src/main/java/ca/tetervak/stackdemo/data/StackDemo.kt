package ca.tetervak.stackdemo.data

import ca.tetervak.stackdemo.domain.StackItem

class StackDemo {
    // the first item of this list is the bottom of the stack
    private val list: MutableList<String> = mutableListOf()

    val itemCount: Int
        get() = list.size

    // the first item of this list is the top of the stack
    val items: List<StackItem>
        get() = buildList(itemCount) {
            for ((index, value) in list.withIndex().reversed()) {
                add(StackItem(index + 1, value))
            }
        }

    fun push(value: String) = list.add(value)

    fun pop(): String = list.removeLast()
}