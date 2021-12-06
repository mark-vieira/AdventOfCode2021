fun main() {
    val oxygen = Integer.parseInt(find(input, ::most).first(), 2)
    val scrubber = Integer.parseInt(find(input, ::least).first(), 2)

    println(oxygen * scrubber)
}

tailrec fun find(list: List<String>, common: (List<String>, Int) -> Char, bit: Int = 0): List<String> {
    val filter = common(list, bit)
    val candidates = list.filter { it[bit] == filter }
    return candidates.takeIf { it.size == 1 } ?: find(candidates, common, bit + 1)
}

fun most(list: List<String>, bit: Int): Char {
    val count = list.fold(0) { arr, code -> if (code[bit] == '1') arr + 1 else arr }
    return if (count >= list.size / 2.0) '1' else '0'
}

fun least(list: List<String>, bit: Int): Char {
    return most(list, bit).let { if (it == '1') '0' else '1' }
}