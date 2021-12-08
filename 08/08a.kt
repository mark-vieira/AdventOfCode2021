import java.io.File

val input = File("08/input.txt").readLines()

fun main() {
    val outputs = input.map { it.split("|")[1].trim() }.flatMap { it.split(" ") }
    println(outputs.count { setOf(2, 4, 3, 7).contains(it.length) })
}