import java.io.File
import java.util.*

val input = File("10/input.txt").readLines()

fun main() {
    val points = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val result = input.map { line ->
        val open: Deque<Char> = LinkedList()
        line.forEach { token ->
            when (token) {
                ')' -> if (open.pollLast() != '(') return@map token
                ']' -> if (open.pollLast() != '[') return@map token
                '}' -> if (open.pollLast() != '{') return@map token
                '>' -> if (open.pollLast() != '<') return@map token
                else -> open.addLast(token)
            }
        }
    }
        .filterIsInstance<Char>()
        .map { c -> points.getValue(c) }
        .reduce { acc, i -> acc + i }


    println(result)
}