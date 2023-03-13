
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
}
fun depthFirstValues(root: Node) {
    /* Depth-First-Traversal
    Time Complexity = O(n) each node is visited only once
    Space Complexity = O(n)
 */
    val stack = Stack<Node>()
    stack.push(root)
    // List to add value of each node
    val result: MutableList<Any> = mutableListOf()
    while (!stack.isEmpty()) {
        val current = stack.pop()
        result.add(current.value)
        current.right?.let { stack.push(it) }
        current.left?.let { stack.push(it) }
    }
    result.forEach { println(it) }
}

fun recursiveDepthFirstTraversal(root: Node?): List<Any> {
    root?.let {
        val leftValues = recursiveDepthFirstTraversal(root.left)
        val rightValues = recursiveDepthFirstTraversal(root.right)
        return listOf(root.value) + leftValues + rightValues
    }
    return emptyList()
}

fun main() {
    //Nodes declaration
    /*
        A
      /    \
     B      C
   /   \      \
  D     E      F
 */

    val a = Node('A')
    val b = Node('B')
    val c = Node('C')
    val d = Node('D')
    val e = Node('E')
    val f = Node('F')
    //Setting left and right's
    a.left = b
    a.right = c
    b.left = d
    b.right = e
    c.right = f
    depthFirstValues(a)

    /*
    Depth-First-Traversal using Recursion
     */
    val result = recursiveDepthFirstTraversal(a)
    println(result)
}
