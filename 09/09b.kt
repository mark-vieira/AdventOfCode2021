fun main() {
    val result = input.flatMapIndexed { y, line -> line.mapIndexed { x, _ -> x to y } }
        .filter { (x, y) -> input.isLowPoint(x, y) }
        .map { (x, y) -> input.walkBasin(x, y).size }
        .sortedDescending()
        .take(3)
        .reduce { acc, i -> acc * i }

    println(result)
}

fun List<IntArray>.walkBasin(x: Int, y: Int): Set<Pair<Int, Int>> {
    val found = mutableSetOf(x to y)
    for ((x2, y2) in setOf(x to y - 1, x to y + 1, x - 1 to y, x + 1 to y)) {
        val adjacent = this.getOrNull(y2)?.getOrNull(x2) ?: 0
        if (this[y][x] < adjacent && adjacent != 9)
            found.addAll(this.walkBasin(x2, y2))
    }

    return found
}