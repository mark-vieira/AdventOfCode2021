fun main() {
    val counts = input.groupBy { it }.mapValues { e -> e.value.size.toLong() }.toMutableMap()
    repeat(256) {
        val new = counts.remove(0) ?: 0
        for (age in 1..8) {
            counts[age - 1] = counts.getOrDefault(age - 1, 0) + counts.getOrDefault(age, 0)
            counts[age] = 0
        }
        counts[8] = counts.getOrDefault(8, 0) + new
        counts[6] = counts.getOrDefault(6, 0) + new
    }

    println(counts.values.sum())
}