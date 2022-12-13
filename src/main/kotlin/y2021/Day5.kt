package y2021;

import utils.Point
import utils.split
import utils.toIntList
import java.io.File
import kotlin.math.abs

private fun step1(data: List<String>) {

    val directions = data.split(" -> ") { it.toIntList(",") }

    val points = directions.map { Point(it[0][0], it[0][1]) to Point(it[1][0], it[1][1]) }

    val (HouV, Diago) = points.partition { it.first.x == it.second.x || it.first.y == it.second.y }

    val representation = HouV.fold(mutableMapOf<Int, MutableMap<Int, Int>>()) { acc, ligne ->
        for (x in minOf(ligne.first.x, ligne.second.x)..maxOf(ligne.first.x, ligne.second.x)) {
            for (y in minOf(ligne.first.y, ligne.second.y)..maxOf(ligne.first.y, ligne.second.y)) {
                acc.putIfAbsent(y, mutableMapOf())
                val ligne = acc[y]!!
                ligne.put(x, ligne.getOrDefault(x, 0) + 1)
            }
        }
        acc
    }

    println(representation.values.flatMap { it.values.map { it } }.count { it > 1 })
    Diago.fold(representation) { acc, ligne ->
        val dx = -ligne.first.x + ligne.second.x
        val d = abs(dx)

        val dy = - ligne.first.y + ligne.second.y
        val xx = dx / d
        val yy = dy / d
        for (i in 0..d) {
            val x = ligne.first.x + i * xx
            val y = ligne.first.y + i * yy
            acc.putIfAbsent(y, mutableMapOf())
            val ligne = acc[y]!!
            ligne.put(x, ligne.getOrDefault(x, 0) + 1)
        }
        acc
    }

    println(representation.values.flatMap { it.values.map { it } }.count { it > 1 })

}


fun main() {


    val data = File("src/main/resources/y2021/day5.txt").readLines()//readBytes().decodeToString().split("\n")
    step1(data)



    //step1(File("src/main/resources/y2021/day3_test.txt").readLines())
    //step2(File("src/main/resources/y2021/day3_test.txt").readLines())
}




