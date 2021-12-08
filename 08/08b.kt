fun main() {
    val entries = input.map { it.split("|").map(String::trim) }
        .map { it[0].split(" ").map { l -> l.toSortedSet().joinToString("") } to it[1].split(" ").map { r -> r.toSortedSet().joinToString("") } }

    println(entries.sumOf { (input, output) -> val code = decode(input); output.map { code[it] }.joinToString("").toInt() })
}

fun decode(input: List<String>): Map<String, Int> {
    val code = mutableMapOf<Int, String>()
    code[1] = input.find { it.length == 2 }!!
    code[4] = input.find { it.length == 4 }!!
    code[7] = input.find { it.length == 3 }!!
    code[8] = input.find { it.length == 7 }!!
    code[3] = input.find { it.length == 5 && it.toList().intersect(code[1]!!.toSet()).size == 2 }!!
    code[9] = input.find { it.length == 6 && it.toList().intersect(code[3]!!.toSet()).size == 5 }!!
    val e = mutableListOf('a','b','c','d','e','f','g').apply { removeAll(code[9]!!.toList()) }.first()
    code[2] = input.find { it.length == 5 && it != code[3]!! && it.contains(e) }!!
    code[5] = input.find { it.length == 5 && it != code[3]!! && it != code[2]!! }!!
    code[0] = input.find { it.length == 6 && it.toList().intersect(code[1]!!.toSet()).size == 2 && it != code[9] }!!
    code[6] = input.find { it.length == 6 && it != code[0]!! && it != code[9]!! }!!

    return code.map { e -> e.value to e.key }.toMap()
}