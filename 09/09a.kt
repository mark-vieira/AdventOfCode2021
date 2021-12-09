import java.io.File

val input = File("09/input.txt").readLines().map { line -> line.map { c -> c.digitToInt() }.toIntArray() }

fun main() {
    println(input.flatMapIndexed { y, line -> line.filterIndexed { x, _ -> input.isLowPoint(x, y) }.map { it + 1 } }.sum())
}

fun List<IntArray>.isLowPoint(x: Int, y: Int): Boolean {
    return setOf(x to y - 1, x to y + 1, x - 1 to y, x + 1 to y).none { (x2, y2) ->
        this[y][x] >= (this.getOrNull(y2)?.getOrNull(x2) ?: Int.MAX_VALUE)
    }
}