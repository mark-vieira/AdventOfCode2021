import java.io.File
import kotlin.math.absoluteValue

val points = File("13/input.txt").readLines().takeWhile(String::isNotBlank).map { it.split(",") }.map { (x, y) -> x.toInt() to y.toInt() }
val instructions = File("13/input.txt").readLines().mapNotNull { Regex("fold along (x|y)=(\\d+)").find(it)?.destructured }.map { (axis, line) -> axis to line.toInt() }

fun main() {
    val grid = Array(points.maxOf { (_, y) -> y } + 1) { IntArray(points.maxOf { (x, _) -> x } + 1) }
    points.forEach { (x, y) -> grid[y][x] = 1 }

    println(fold(grid, instructions[0]).flatMap(IntArray::asIterable).count { it == 1 })
}

fun fold(grid: Array<IntArray>, instruction: Pair<String, Int>): Array<IntArray> {
    val (axis, line) = instruction
    for (y in if (axis == "y") (line + 1) until grid.size else grid.indices) {
        for (x in if (axis == "x") (line + 1) until grid[0].size else grid[0].indices) {
            if (axis == "y") {
                grid[y][x].takeIf { it == 1 }?.also { grid[(y - (line * 2)).absoluteValue][x] = 1 }
            } else {
                grid[y][x].takeIf { it == 1 }?.also { grid[y][(x - (line * 2)).absoluteValue] = 1 }
            }
        }
    }
    return if (axis == "y") {
        grid.take(line).toTypedArray()
    } else {
        grid.map { it.take(line).toIntArray() }.toTypedArray()
    }
}