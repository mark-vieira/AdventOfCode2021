import java.io.File

val input = File("11/input.txt").readLines().map { it.map(Char::digitToInt).toIntArray() }

fun main() {
    var flashes = 0
    repeat(100) {
        input.forEachPoint { x, y, value ->
            input[y][x] = value + 1
        }
        input.forEachPoint { x, y, value ->
            if (value > 9) input.flash(x, y) { flashes++ }
        }
    }

    println(flashes)
}

fun List<IntArray>.forEachPoint(block: (x: Int, y: Int, value: Int) -> Unit) {
    this.forEachIndexed { y, row ->
        row.forEachIndexed { x, i ->
            block(x, y, i)
        }
    }
}

fun List<IntArray>.flash(x: Int, y: Int, block: () -> Unit = {}) {
    block()
    this[y][x] = 0
    for (x2 in x-1..x+1) {
        for (y2 in y-1..y+1) {
            if (x2 != x || y2 != y) {
                getOrNull(y2)?.getOrNull(x2)?.takeIf { it != 0 }?.also { value ->
                    this[y2][x2] = value + 1
                    if (value + 1 > 9) flash(x2, y2, block)
                }
            }
        }
    }
}