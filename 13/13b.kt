fun main() {
    instructions.fold(grid) { grid, instruction -> grid.fold(instruction) }.print()
}

fun Array<IntArray>.print() {
    this.forEach { row ->
        row.forEach { print(if (it == 1) "# " else "  ") }
        print("\n")
    }
}