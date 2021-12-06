fun main() {
    val measurements = input.dropLast(2).mapIndexed { index, i -> i + input[index + 1] + input[index + 2] }
    println(measurements.drop(1).filterIndexed { index, i -> i > measurements[index] }.count())
}