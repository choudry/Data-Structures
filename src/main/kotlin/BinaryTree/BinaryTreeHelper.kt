package BinaryTree

data class Node (
    var value: Any
) {
    var left: Node? = null
    var right: Node? = null
}

class Stack<T> {
    private val data: MutableList<T> = mutableListOf()

    fun push(input: T) {
        data.add(0, input)
    }

    fun pop(): T = data.removeAt(0)

    fun isEmpty() = data.isEmpty()

    fun isNotEmpty() = data.isNotEmpty()
}

/**
    Queue is a pipeline where value enters from one end and leaves from the other end.
 */
class Queue<U> {
    private val data: MutableList<U> = mutableListOf()

    fun push(item: U) {
        data.add(0, item)
    }

    fun shift() = data.removeAt(data.size - 1)

    fun isEmpty() = data.isEmpty()

    fun isNotEmpty() = data.isNotEmpty()
}