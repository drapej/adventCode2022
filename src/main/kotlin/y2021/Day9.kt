package y2021;

import utils.Point
import java.io.File


private fun step1(data: List<String>) {

    val datas = data.mapIndexed { x, it -> it.chunked(1).mapIndexed { y, value -> Point(x, y, value.toInt()) } }
    val nbreLignes = data.size
    val nbreCol = datas.maxOf { it.size }

    val pointsBas = mutableListOf<Point>()
    repeat(nbreLignes) { x ->
        repeat(nbreCol) { y ->
            val point = datas[x][y]
            val quadrant = getQuadrants(datas, point)
            if (quadrant.all { it.value > point.value }) {
                pointsBas.add(point)
            }
        }
    }
    println("Step 1 : ${pointsBas.sumOf { it.value + 1 }}")

    val allreadySeen = mutableSetOf<Point>()


    fun find(point: Point): Int {
        if (point in allreadySeen) {
            return 0
        }
       // if (point.value == 9 ) return 0
        allreadySeen.add(point)
        return 1 + getQuadrants(datas, point).filter { it.value < 9 && it !in allreadySeen }.map {
            val nombre = find(it)
            nombre
        }.sum()
    }


    val bassins = pointsBas.map { point ->
        val nombre = find(point)
        allreadySeen.clear()
        nombre
    }


    val step2 = bassins.sortedDescending().take(3).fold(1) { acc, bassin -> acc * bassin }
    println("Step 2 : $step2")

}

private fun getQuadrants(
    datas: List<List<Point>>,
    point: Point
): List<Point> {
    val x = point.x
    val y = point.y
    val top = datas.getOrNull(x - 1)?.getOrNull(y)
    val bottom = datas.getOrNull(x + 1)?.getOrNull(y)
    val left = datas.getOrNull(x)?.getOrNull(y - 1)
    val right = datas.getOrNull(x)?.getOrNull(y + 1)
    return listOfNotNull(top, bottom, left, right)
}

fun main() {
    val data = File("src/main/resources/y2021/day9.txt").readLines()

    val datatest = """2199943210
3987894921
9856789892
8767896789
9899965678""".split("\n")

    step1(data)

}