import kotlin.math.absoluteValue

fun main() {
    val max = input.maxOrNull()!!
    val start = max / 2
    val mapper = { list: List<Int>, i: Int -> list.sumOf { (1..(it - i).absoluteValue).sum() } }
    println(search(input, start, 0..max, mapper(input, start), mapper))
}