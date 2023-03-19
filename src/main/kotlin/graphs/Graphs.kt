package graphs

import Helper.Queue
import Helper.Stack

/* Graph
    a --------> c
    |           |
    |           |
    v           V
    b           e
    |
    |
    V
    d --------> f
 */

fun graphBreadthFirstTraversal(graph: Map<Char, List<Char>>, source: Char) {
    val queue = Queue<Char>()
    queue.push(source)
    var path = ""
    while (queue.isNotEmpty()) {
        val current = queue.shift()
        path += current
        graph[current]?.forEach {
            queue.push(it)
        }
    }

    println("Breadth First Path: $path")
}

fun graphDepthFirstTraversal(graph: Map<Char, List<Char>>, source: Char) {
    val stack = Stack<Char>()
    stack.push(source)
    var path = ""
    while (stack.isNotEmpty()) {
        val current = stack.pop()
        path += current
        graph[current]?.forEach { stack.push(it) }
    }
    println("Depth First Path: $path")
}

fun graphDepthFirstTraversalRecursive(graph: Map<Char, List<Char>>, source: Char) {
    println(source)
    graph.get(source)?.forEach { node ->
        graphDepthFirstTraversalRecursive(graph, node)
    }
}

fun main() {
    val graph: Map<Char, List<Char>> = mapOf(
        'a' to listOf('b', 'c'),
        'b' to listOf('d'),
        'c' to listOf('e'),
        'd' to listOf('f'),
        'e' to emptyList(),
        'f' to emptyList()
    )

    graphBreadthFirstTraversal(graph, 'a')
    graphDepthFirstTraversal(graph, 'a')
    graphDepthFirstTraversalRecursive(graph, 'a')
}