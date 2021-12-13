import java.io.File
import kotlin.math.absoluteValue

val lines = File("13/input.txt").readLines()
val points = lines.takeWhile(String::isNotBlank).map { it.split(",") }.map { (x, y) -> x.toInt() to y.toInt() }
val instructions = lines.mapNotNull { Regex("fold along (x|y)=(\\d+)").find(it)?.destructured }.map { (axis, line) -> axis to line.toInt() }
val grid = points.fold(Array(points.maxOf { (_, y) -> y } + 1) { IntArray(points.maxOf { (x, _) -> x } + 1) }) { grid, (x, y) -> grid[y][x] = 1; grid }

fun main() {
    println(grid.fold(instructions[0]).flatMap(IntArray::asIterable).count { it == 1 })
}

fun Array<IntArray>.fold(instruction: Pair<String, Int>): Array<IntArray> {
    val (axis, line) = instruction
    for (y in if (axis == "y") (line + 1) until this.size else this.indices) {
        for (x in if (axis == "x") (line + 1) until this[0].size else this[0].indices) {
            if (axis == "y") {
                this[y][x].takeIf { it == 1 }?.also { this[(y - (line * 2)).absoluteValue][x] = 1 }
            } else {
                this[y][x].takeIf { it == 1 }?.also { this[y][(x - (line * 2)).absoluteValue] = 1 }
            }
        }
    }
    return if (axis == "y") {
        this.take(line).toTypedArray()
    } else {
        this.map { it.take(line).toIntArray() }.toTypedArray()
    }
}