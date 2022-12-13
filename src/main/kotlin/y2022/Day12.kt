package y2022;

import prev
import utils.Point
import utils.getOthersDir
import utils.toPoint
import java.io.File

fun trueValue(value: String) =
    value.replace("E", "z"[0].inc().toString()).replace("S", "a"[0].dec().toString()).first().code

private fun Point.trueValue() =
    this.valueStr?.replace("E", "z"[0].inc().toString())?.replace("S", "a"[0].dec().toString())?.first()?.code ?: -1

typealias Trajet = Pair<Point, Point>

private fun step1(data: List<String>) {

    val grid = data.toPoint()
    val start = grid.flatten().find { it.valueStr == "S" }!!
    val end = grid.flatten().find { it.valueStr == "E" }!!

    val points = grid.flatten()

    val allTrajets = points
        .flatMap { point ->
            points
                .getOthersDir(point)
                .filter { voisin -> voisin.trueValue() <= point.trueValue() + 1 }
                .map { Trajet(point, it) }
        }
        .groupBy { it.first }


    val queue = ArrayDeque<Triple<Point, Int, MutableList<Point>>>()

    val visited = mutableSetOf(start)
    queue.add(Triple(start, 0, mutableListOf(start)))

    while(queue.isNotEmpty()){
        val element = queue.removeFirst()


        element.third.forEach {



        }


    }
}

fun main() {

    val data = File("src/main/resources/y2022/day12.txt").readLines()

    val datatest = """Sabqponm
                                abcryxxl
                                accszExk
                                acctuvwj
                                abdefghi""".split("\n")

    step1(data)

}



