
fun merge(input: List<Int>, start: Int, mid: Int, end: Int): MutableList<Int> {

    var i = 0
    var j = mid + 1
    var tempData: MutableList<Int> = input.toMutableList()

    // Build sorted temporary array
    val temp: MutableList<Int> = mutableListOf()
    // while both sub-arrays have values, then try merge them in sorted array
    while(i <= mid && j <= end) {
        if (input[i] < input[j]) {
            temp.add(input[i++])
        } else {
            temp.add(input[j++])
        }
    }

    //Add the rest of values from left sub-arrays
    while(i <= mid) {
        temp.add(input[i++])
    }

    //Add the rest of values from right sub-arrays
    while(j <= end) {
        temp.add(input[j++])
    }

    // Replacing the part of original array with the sorted array
    var k = start
    var m = 0
    while (k <= end && m < temp.count()) {
        tempData[k] = temp[m++]
    }
    return tempData
}

fun mergeSort(_input: List<Int>, start: Int, end: Int): MutableList<Int> {

    var input = _input.toMutableList()
    println("Start: $start, End: $end, input: $input")
    if (start < end) {
        val mid = (start + end) / 2
        input = mergeSort(input, start, mid)
        input = mergeSort(input, mid + 1, end)
        input = merge(input, start, mid, end)
    }
    return input
}



fun main(args: Array<String>) {
    println("Hello World!")

}
