fun main() {
    var x = 0
    var y = 0
    var aim = 0

    input.forEach { command ->
        val (direction, distance) = command.split(" ")
        when (direction) {
            "forward" -> {
                x += distance.toInt()
                y += distance.toInt() * aim
            }
            "down" -> aim += distance.toInt()
            "up" -> aim -= distance.toInt()
        }
    }

    println(x * y)
}