fun main() {
    for (i in 1..drawings.size) {
        val numbers = drawings.take(i)
        if (boards.size == 1 && isWinner(boards[0], numbers)) {
            println(boards[0].flatMap(IntArray::asIterable).filter { !numbers.contains(it) }.sum() * numbers.last())
            return
        }
        boards.removeAll { isWinner(it, numbers) }
    }
}