package graphs

import Helper.Queue
import Helper.Stack


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

/*
 * hasPath function finds out whether a directed path exists between the source and destination nodes.
 * Using iterative approach
 */
fun hasPath(graph: Map<Char, List<Char>>, source: Char, destination: Char): Boolean {
    if (source == destination) return true
    val queue = Queue<Char>()
    queue.push(source)

    while (queue.isNotEmpty()) {
        val current = graph[queue.shift()]
        current?.let {
            it.forEach { element ->
                if (element == destination) return true
                queue.push(element)
            }
        }
    }
    return false
}

/*
    has path method using recursive approach
 */

fun hasPathRecursive(graph: Map<Char, List<Char>>, source: Char, destination: Char): Boolean {
    if (source == destination) return true

    graph[source]?.forEach {
        if (hasPathRecursive(graph, it, destination)) return true
    }
    return false
}

/* Graph
    a --------> c
    |           |
    |           |
    V           V
    b           e
    |
    |
    V
    d --------> f
 */



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

    println("\n ***** Has Path *****")
    println("hasPath(a-f): " + hasPath(graph, 'a', 'f'))
    println("hasPath(f-a): " + hasPath(graph, 'f', 'a'))
    println("\n ***** Has Path Recursive*****")
    println("hasPathRecursive(a-f): " + hasPathRecursive(graph, 'a', 'f'))
    println("hasPathRecursive(b-e): " + hasPathRecursive(graph, 'b', 'e'))
}