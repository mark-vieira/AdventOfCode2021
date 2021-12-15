import java.io.File

val lines = File("14/input.txt").readLines()
val template = lines.take(1).single()
val rules = lines.drop(2).map { it.split("->").map(String::trim) }.associate { (pair, element) -> pair to element.first() }

fun main() {
    var result = template

    repeat(10) {
        result = result.windowed(2, 1)
            .foldIndexed(0 to result.toMutableList()) { i, tuple, pair ->
                rules[pair]?.also { e -> tuple.second.add(i + 1 + tuple.first, e) }
                tuple.first + 1 to tuple.second
            }
            .second
            .joinToString("")
        println(result.length)
    }

    val counts = result.groupBy { it }.mapValues { it.value.size }
    println(counts.maxOf { it.value } - counts.minOf { it.value })
}