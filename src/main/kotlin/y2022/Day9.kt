package y2022;

import utils.Point
import utils.split
import java.io.File
import java.lang.Math.abs
import kotlin.math.sign


private fun step1(data: List<String>) {
    val instrs = data.split(" ").map { it[0] to it[1].toInt() }
    val posH = Point(0, 0)
    var posQ = Point(0, 0)

    val pos = mutableSetOf(posQ.pos())
    instrs.forEach { instr ->
        repeat(instr.second) {
            val dist = posH.distance(posQ)
            val previousPosH = posH.copy()
            posH.move(instr.first)
            if (!posH.isBeside(posQ)) {
                if (dist > 1) posQ = previousPosH
                else posQ.move(instr.first)
                pos.add(posQ.pos())
            }
        }
    }
    println(pos.size)

}

private fun step2(data: List<String>, size : Int) {
    val instrs = data.split(" ").map { it[0] to it[1].toInt() }
    val rope = (1..size).map { Point() }.toMutableList()

    fun newCoord(diff : Int, head: Int, tail : Int) = if (abs(diff) == 2 ) diff.sign + tail else head

    val pos = mutableSetOf<Point>()
    instrs.forEach { instr ->
        repeat(instr.second) {
            rope[0] = rope[0].moveC(instr.first)

            (1 .. size-1).forEach{ i ->
                val (toFollow, current) = rope[i - 1] to rope[i]
                if (!toFollow.isBeside(current)) {
                    val diff = toFollow-current

                    val newX = newCoord(diff.x, toFollow.x, current.x)
                    val newY = newCoord(diff.y, toFollow.y, current.y)

                    rope[i] = Point(newX, newY)
                }
            }
            pos.add(rope.last())
        }
    }

    //logPoint(pos, "H")
    println("$size : ${pos.size}")

}

fun logPoint(posQ: Iterable<Point>, str: String? = null) {
    val minX = (posQ).minBy { it.x }.x - 5
    val maxX = (posQ).maxBy { it.x }.x + 5
    val minY = (posQ).minBy { it.y }.y - 5
    val maxY = (posQ).maxBy { it.y }.y + 5
    val points = posQ
    for (y in minY..maxY) {
        for (x in minX..maxX) {
            val p = points.indexOfFirst { it.x == x && it.y == y }

            print(if (p == -1) "." else if (p == 0) "H" else str ?: p)
        }
        println("")
    }
    println("")
}

fun main() {
    val data = File("src/main/resources/y2022/day9.txt").readLines()
    val datatest = """R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2""".split("\n")

    val datatest2 = """R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20""".split("\n")

    //step1(data)
    step2(data, 2)
    step2(data, 10)

}