fun main() {
    var step = 0
    while (input.flatMap(IntArray::asIterable).any { it != 0 }) {
        input.forEachPoint { x, y, value ->
            input[y][x] = value + 1
        }
        input.forEachPoint { x, y, value ->
            if (value > 9) input.flash(x, y)
        }
        step++
    }

    println(step)
}