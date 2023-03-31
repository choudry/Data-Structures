package BinaryTree

import Helper.Node
import Helper.Queue
import Helper.Stack


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

/**
 * Calculate the sum of all the node values of tree
 */
fun treeSum(root: Node?): Int {
    root?.let {
        val queue = Queue<Node>()
        queue.push(root)
        var sum = 0

        while (queue.isNotEmpty()) {
            val current = queue.shift()
            if (current.value is Int) {
                sum += current.value as Int
            }

            current.left?.let { queue.push(it) }
            current.right?.let { queue.push(it) }
        }

        return sum
    }

    return 0
}

fun recursiveTreeSum(root: Node?): Int{
    root?.let {
        return root.value as Int + recursiveTreeSum(root.left) + recursiveTreeSum(root.right)
    }

    return 0
}

/**
 * Find minimum value in tree
*/
fun treeMinValueQueue(root: Node?): Int {
    root?.let {
        val queue = Queue<Node>()
        queue.push(root)
        var smallest = Int.MAX_VALUE
        while (queue.isNotEmpty()) {
            val current = queue.shift()
            val currentValue = current.value as Int
            if (currentValue < smallest) {
                smallest = currentValue
            }

            current.left?.let { queue.push(it) }
            current.right?.let { queue.push(it) }
        }

        return smallest
    }

    return Int.MAX_VALUE
}

fun treeMinValueStack(root: Node?): Int {
    root?.let {
        val stack = Stack<Node>()
        stack.push(root)
        var smallest = Int.MAX_VALUE
        while (stack.isNotEmpty()) {
            val current = stack.pop()
            val currentValue = current.value as Int
            if (currentValue < smallest) {
                smallest = currentValue
            }

            current.left?.let { stack.push(it) }
            current.right?.let { stack.push(it) }
        }

        return smallest
    }

    return Int.MAX_VALUE
}

fun recursiveTreeMinValue(root: Node?): Int {
    root?.let {
        val current = root.value as Int
        val leftMin = recursiveTreeMinValue(root.left)
        val rightMin = recursiveTreeMinValue(root.right)
        return listOf(current, leftMin, rightMin).min()
    }

    return Int.MAX_VALUE
}

/**
 * Max root to leaf path sum is find the maximum value of the path from root to leaf
 */
fun maxPathSum(node: Node?): Int {
    node?.let {
        if (it.left == null && it.right == null) return it.value as Int
        val leftSum = maxPathSum(it.left) + it.value as Int
        val rightSum = maxPathSum(it.right) + it.value as Int
        return if (leftSum > rightSum) {
             leftSum
        } else {
          rightSum
        }
    }
    return Int.MIN_VALUE
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

    println("\n ***** Tree Sum *****")
    val one = Node(1)
    val two = Node(2)
    val three = Node(3)
    val four = Node(4)
    val five = Node(5)
    val six = Node(6)

    one.left = two
    one.right = three
    two.left = four
    two.right = five
    three.right = six
    /*
        1
     /     \
    2       3
  /   \       \
 4     5       6

  */
    println("Sum of tree: ${treeSum(one)}")
    println("Recursive sum of tree: ${recursiveTreeSum(two)}")

    println("\n ***** Tree minimum value *****")
    println("Tree min value using stack: ${treeMinValueStack(one)}")
    println("Tree min value using queue: ${treeMinValueQueue(one)}")
    println("Tree min value using recursion: ${recursiveTreeMinValue(one)}")

    println("\n ***** Max path sum *****")
    println("Max path sum value is: ${maxPathSum(one)}")
}
