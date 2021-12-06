import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

val input = File("05/input.txt").readLines()
    .map { Regex("(\\d+),(\\d+) -> (\\d+),(\\d+)").find(it)!!.destructured }
    .map { (x1, y1, x2, y2) -> Pair(Point(x1, y1), Point(x2, y2)) }

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    constructor(x: String, y: String) : this(x.toInt(), y.toInt())

    operator fun rangeTo(that: Point): PointRange {
        val start = if (this <= that) this else that
        val end = if (start == this) that else this
        return PointRange(start, end)
    }

    override fun compareTo(other: Point): Int = this.x.compareTo(other.x)
}

class PointRange(override val start: Point, override val endInclusive: Point) : Iterable<Point>, ClosedRange<Point> {
    override fun iterator(): Iterator<Point> {
        val points = mutableListOf<Point>()
        if (start.x == endInclusive.x || start.y == endInclusive.y ) {
            for (x in start.x..endInclusive.x) {
                for (y in min(start.y, endInclusive.y)..max(start.y, endInclusive.y)) {
                    points.add(Point(x, y))
                }
            }
        } else {
            val slope = if (start.y < endInclusive.y) 1 else -1
            (start.x..endInclusive.x).forEachIndexed { y, x ->
                points.add(Point(x, start.y + (y * slope)))
            }
        }
        return points.iterator()
    }

    override fun contains(value: Point): Boolean = iterator().asSequence().contains(value)
}

fun main() {
    val lines = input.filter { (l, r) -> l.x == r.x || l.y == r.y }
    val maxX = lines.flatMap { (l, r) -> listOf(l.x, r.x) }.maxOrNull() ?: 0
    val maxY = lines.flatMap { (l, r) -> listOf(l.y, r.y) }.maxOrNull() ?: 0
    val grid = Array(maxY + 1) { IntArray(maxX + 1) }

    lines.forEach { (l, r) ->
        for (p in l..r) {
            grid[p.y][p.x]++
        }
    }

    println(grid.flatMap(IntArray::asIterable).count { it >= 2 })
}