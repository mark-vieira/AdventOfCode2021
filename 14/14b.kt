fun main() {
    val pairs = template.windowed(2, 1).groupBy { it }.mapValues { it.value.size.toLong() }.toMutableMap()
    val counts = template.groupBy { it }.mapValues { it.value.count().toLong() }.toMutableMap()

    repeat(40) {
        pairs.filterValues { it > 0 }.toMap().forEach { (pair, count) ->
            rules[pair]?.let { e ->
                counts.compute(e) { _, v -> v?.plus(count) ?: count }
                pairs.compute(pair) { _, v -> v?.minus(count) }
                pairs.compute("${pair[0]}$e") { _, v -> v?.plus(count) ?: count }
                pairs.compute("$e${pair[1]}") { _, v -> v?.plus(count) ?: count }
            }
        }
    }

    println(counts.maxOf { it.value } - counts.minOf { it.value })
}

