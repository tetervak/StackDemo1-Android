package ca.tetervak.stackdemo.domain

class StackDemo {

    private val list: MutableList<String> = mutableListOf()

    val itemCount: Int
        get() = list.size

    val items: List<StackItem>
        get() = buildList(itemCount) {
            for ((index, value) in list.withIndex().reversed()) {
                add(StackItem(index + 1, value))
            }
        }

    fun push(value: String) = list.add(value)

    fun pop(): String = list.removeLast()
}