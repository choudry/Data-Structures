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

/**
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

/*
In undirected graph edges represents bidirectional path
 */

/*
 * undirectedPath takes in an array of edges for an undirected graph and two nodes(nodeA, nodeB) and returns a boolean
 * indicating whether there exists a path between nodeA and nodeB
 */

/**
 * hasPath method for undirected graph with iterative approach
 */
fun undirectedPath(edges: List<Pair<Char, Char>>, source: Char, destination: Char): Boolean {
    if (source == destination) return true
    val graph = buildGraph(edges)
    val queue = Queue<Char>()
    val visited = mutableSetOf<Char>()
    queue.push(source)
    visited.add(source)
    while (queue.isNotEmpty()) {
        val current = queue.shift()
        if (current == destination) return true
        graph[current]?.forEach {
            if (!visited.contains(it)) {
                visited.add(it)
                queue.push(it)
            }
        }
    }
    return false
}

/**
 * hasPath method for undirected graph with recursive approach
 */
fun buildGraph(edges: List<Pair<Char, Char>>): Map<Char, List<Char>> {
    val graph: MutableMap<Char, MutableList<Char>> = mutableMapOf()

    edges.forEach { edge ->
        val (a, b) = edge
        if (graph[a] == null) graph[a] = mutableListOf()
        if (graph[b] == null) graph[b] = mutableListOf()
        graph[a]?.add(b)
        graph[b]?.add(a)
    }

    return graph
}

fun undirectedPathRecursive(edges: List<Pair<Char, Char>>, source: Char, destination: Char): Boolean {
    val graph = buildGraph(edges)
    return hasEdge(graph, source, destination, mutableSetOf())
}

fun hasEdge(edges: Map<Char, List<Char>>, source: Char, destination: Char, visited: MutableSet<Char>): Boolean {
    if (visited.contains(source)) return false
    if (source == destination) return true
    if (visited.contains(source)) return false
    edges[source]?.forEach { neighbor ->
        if (neighbor == destination) return true
        else {
            visited.add(neighbor)
        }
    }

    return false
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

    println("\n ***** Has Path *****")
    println("hasPath(a-f): " + hasPath(graph, 'a', 'f'))
    println("hasPath(f-a): " + hasPath(graph, 'f', 'a'))
    println("\n ***** Has Path Recursive*****")
    println("hasPathRecursive(a-f): " + hasPathRecursive(graph, 'a', 'f'))
    println("hasPathRecursive(b-e): " + hasPathRecursive(graph, 'b', 'e'))

    // Undirected Graph
    println("\n ***** Undirected Graph *****")
    val nodes = listOf(
        Pair('a', 'b'),
        Pair('c', 'd'),
        Pair('e', 'f'),
        Pair('a', 'c')
    )
    println("Undirected graph hasPath(a - f): " + undirectedPath(nodes, 'a', 'f'))
    println("Undirected graph hasPath(a - c): " + undirectedPath(nodes, 'a', 'c'))
    println("Undirected graph hasPath(c - a): " + undirectedPath(nodes, 'c', 'a'))

    println("Undirected graph hasPath(c - a) recursive: " + undirectedPathRecursive(nodes, 'c', 'a'))
    println("Undirected graph hasPath(a - f) recursive: " + undirectedPathRecursive(nodes, 'a', 'f'))


}