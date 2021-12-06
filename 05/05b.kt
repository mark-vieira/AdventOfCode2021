fun main() {
    val maxX = input.flatMap { (l, r) -> listOf(l.x, r.x) }.maxOrNull() ?: 0
    val maxY = input.flatMap { (l, r) -> listOf(l.y, r.y) }.maxOrNull() ?: 0
    val grid = Array(maxY + 1) { IntArray(maxX + 1) }

    input.forEach { (l, r) ->
        for (p in l..r) {
            grid[p.y][p.x]++
        }
    }

    println(grid.flatMap(IntArray::asIterable).count { it >= 2 })
}