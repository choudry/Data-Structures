package BinaryTree

fun depthFirstTraversal(root: Node): List<Any> {
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

    return result
}

fun recursiveDepthFirstTraversal(root: Node?): List<Any> {
    root?.let {
        val leftValues = recursiveDepthFirstTraversal(root.left)
        val rightValues = recursiveDepthFirstTraversal(root.right)
        return listOf(root.value) + leftValues + rightValues
    }
    return emptyList()
}
    //***********Breadth-First-Traversal*************
/**
 * Breadth first traversal can be done using queue only, so there's no recursive approach
 * Time complexity = O(n) as every node is visited only once
 * Space complexity = O(n) as every node is saved only once
 */
fun breadthFirstTraversal(root: Node?): List<Any> {
        root?.let {
            val queue = Queue<Node>()
            val values: MutableList<Any> = mutableListOf()
            queue.push(root)

            while (queue.isNotEmpty()) {
                val current = queue.shift()
                values.add(current.value)

                current.left?.let { queue.push(it) }
                current.right?.let { queue.push(it) }
            }

            return values
        }

        return emptyList()
}

/**
    Tree include problem is to find out  either tree includes a specific value. We can find it with depth first search as well as breadth first
    search
 */
fun treeIncludesBreadthFirst(root: Node?, target: Any): Boolean {
    root?.let {
        val queue = Queue<Node>()
        queue.push(it)
        while (queue.isNotEmpty()) {
            val currentNode = queue.shift()
            if (currentNode.value == target) return true
            currentNode.left?.let { leftNode ->
                queue.push(leftNode)
            }

            currentNode.right?.let { rightNode ->
                queue.push(rightNode)
            }
        }
    }

    return false
}

fun treeIncludesDepthFirst(root: Node?, target: Any): Boolean {
    root?.let {
        if (it.value == target) return true
        it.left?.let { leftNode ->
            treeIncludesBreadthFirst(leftNode, target)
        }

        it.right?.let { rightNode ->
            treeIncludesBreadthFirst(rightNode, target)
        }
    }

    return false
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

    println("Depth-First-Traversal: ${depthFirstTraversal(a)}")
    println("Depth-First-Traversal(Recursive): ${recursiveDepthFirstTraversal(a)}")
    println("\n *****Breadth-First-Traversal*****\n")
    println("Breadth-First-Traversal: ${breadthFirstTraversal(a)}")

    println("\n *****Search Value*****")
    println("Breadth-First-Search: ${treeIncludesBreadthFirst(a, 3)}")
    println("Depth-First-Search: ${treeIncludesDepthFirst(c, 'F')}")
}
