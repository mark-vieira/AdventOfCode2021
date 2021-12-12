fun main() {
    buildGraph(input, caves)
    println(caves.find { it.name == "start" }?.paths2()?.size)
}

fun Node.paths2(end: String = "end", current: List<String> = listOf(this.name)): List<List<String>> {
    val paths = mutableListOf<List<String>>()
    for (node in nodes) {
        if (node.name == end) {
            paths.add(current.plus(node.name))
        } else if (node.name != "start") {
            val visited = current.plus(node.name).filter { it.any(Char::isLowerCase) }.groupBy { it }.mapValues { it.value.size }
            if (visited.count { it.value == 2 } <= 1 && visited.count { it.value > 2 } == 0)
                paths.addAll(node.paths2(end, current.plus(node.name)))
        }
    }
    return paths
}