
data class Node (
    var value: Int,
    var left: Int,
    var right: Int
)

class Stack {
    private val data: MutableList<Any> = mutableListOf()

    fun push(input: Any) {
        data.add(0, input)
    }

    fun pop(): Any = data.removeAt(0)
}

class BinaryTree {

}
