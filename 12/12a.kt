import java.io.File

val input = File("12/input.txt").readLines().map { line -> line.split("-")}.map { (l, r) -> l to r }
val caves = input.flatMap { listOf(Node(it.first), Node(it.second)) }.distinct()

fun main() {
    buildGraph(input, caves)
    println(caves.find { it.name == "start" }?.paths()?.size)
}

fun buildGraph(input: List<Pair<String, String>>, nodes: List<Node>) {
    input.map { (l, r) -> nodes.find { it.name == l }!! to nodes.find { it.name == r }!! }
        .forEach { (l, r) -> l.link(r) }
}

data class Node(val name: String, internal var nodes: MutableSet<Node> = mutableSetOf()) {
    fun paths(end: String = "end", current: List<String> = listOf(this.name)): List<List<String>> {
        val paths = mutableListOf<List<String>>()
        for (node in nodes) {
            if (node.name == end) {
              paths.add(current.plus(node.name))
            } else if (!current.contains(node.name) || node.name.all(Char::isUpperCase)) {
                paths.addAll(node.paths(end, current.plus(node.name)))
            }
        }
        return paths
    }

    fun link(node: Node) {
        this.nodes.add(node)
        node.nodes.add(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (name != (other as Node).name) return false
        return true
    }

    override fun hashCode(): Int = name.hashCode()

    override fun toString(): String = "Node(name='$name')"
}