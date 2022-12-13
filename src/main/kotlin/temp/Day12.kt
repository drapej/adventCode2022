package temp

import me.grison.aoc.at
import java.io.File

fun main() {
    val data = File("src/main/resources/y2022/day12.txt").readLines()
    val datatest = """Sabqponm
                                abcryxxl
                                accszExk
                                acctuvwj
                                abdefghi""".split("\n")

    val datatest2 = """Sabqponm
accryxxl
accszExk
acctuvwj
acdefghi
acdddddd
acdddddd
acdddddd
acdddddd
acdddddd
acdddddd
acdddddd
abdddddd""".split("\n")

    println(solve(datatest2, "S"))
    println(solve(data, "a"))

}

private fun cellValue(x: String) =
    x.replace("S", "a").replace("E", "z").first().code


private fun solve(data : List<String>, start: String): Int {
    val grid = data.stringGrid("z")
    val starts = grid.allPositions().filter { grid.at(it) == start }
    val end = grid.allPositions().find { grid.at(it) == "E" }!!

    val edges = mutableListOf<Pair<Position, Position>>()
    grid.allPositions().forEach { p ->
        for (dir in p.directions()) {
            if (cellValue(grid.at(dir)) <= cellValue(grid.at(p)) + 1) {
                edges.add(p(p, dir))
            }
        }
    }

    val graph = edges.toDirectedGraph()
    return starts.map { graph.shortestPath(it, end).first }.filter { it > -1 }.minOrNull()!!
}
