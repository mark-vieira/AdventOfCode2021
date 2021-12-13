fun main() {
    var grid = Array(points.maxOf { (_, y) -> y } + 1) { IntArray(points.maxOf { (x, _) -> x } + 1) }
    points.forEach { (x, y) -> grid[y][x] = 1 }
    instructions.forEach { grid = fold(grid, it) }
    grid.print()
}

fun Array<IntArray>.print() {
    this.forEach { row ->
        row.forEach { print(if (it == 1) "# " else "  ") }
        print("\n")
    }
}