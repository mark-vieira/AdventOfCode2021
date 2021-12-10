import java.util.*

fun main() {
    val points = mapOf('(' to 1, '[' to 2, '{' to 3, '<' to 4)
    val result = input.map { line ->
        val open: Deque<Char> = LinkedList()
        line.forEach { token ->
            when (token) {
                ')' -> if (open.pollLast() != '(') return@map false to listOf<Char>()
                ']' -> if (open.pollLast() != '[') return@map false to listOf<Char>()
                '}' -> if (open.pollLast() != '{') return@map false to listOf<Char>()
                '>' -> if (open.pollLast() != '<') return@map false to listOf<Char>()
                else -> open.addLast(token)
            }
        }
        true to open
    }
        .mapNotNull { it.takeIf { it.first }?.second }
        .map { it.reversed().fold(0L) { acc, c -> (acc * 5) + points.getValue(c) } }
        .sorted()

    println(result[result.size / 2])
}