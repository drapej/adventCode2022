package utils

import kotlin.math.abs
import kotlin.math.sign

data class Point(var x: Int = 0, var y: Int = 0, var value: Int = 0, var valueStr: String? = null) {

    fun move(direction: String) {
        when (direction) {
            "R" -> moveR()
            "L" -> moveL()
            "D" -> moveD()
            "U" -> moveU()
        }
    }

    fun moveC(direction: String): Point {
        val newPoint = this.copy()
        newPoint.move(direction)
        return newPoint
    }

    fun addX(nbre: Int = 1) {
        x += nbre
    }

    fun addY(nbre: Int = 1) {
        y += nbre
    }


    fun moveR() {
        x += 1
    }

    fun moveL() {
        x -= 1
    }

    fun moveD() {
        y += 1
    }

    fun moveU() {
        y -= 1
    }

    fun pos() = "${x}_$y"

    fun distance(p: Point) = abs(p.x - this.x) + abs(p.y - this.y)
    fun isBeside(p: Point) = abs(p.x - this.x) <= 1 && abs(p.y - this.y) <= 1
    operator fun minus(p: Point): Point = Point(this.x - p.x, this.y - p.y)
    operator fun plus(p: Point): Point = Point(this.x + p.x, this.y + p.y)

    fun isAligned(p: Point) = this.x - p.x == 0 || this.y - p.y == 0
    fun sign(): Point = x.sign toP y.sign

}

infix fun Int.toP(y: Int): Point = Point(this, y)


fun Collection<String>.toPoint() = this.mapIndexed { index, line ->
    line.chunked(1).mapIndexed { indexCol, value -> Point(index, indexCol, valueStr = value) }
}


fun List<List<Point>>.getQuadrants(point: Point): List<Point> {
    val x = point.x
    val y = point.y
    val top = this.getOrNull(x - 1)?.getOrNull(y)
    val bottom = this.getOrNull(x + 1)?.getOrNull(y)
    val left = this.getOrNull(x)?.getOrNull(y - 1)
    val right = this.getOrNull(x)?.getOrNull(y + 1)
    return listOfNotNull(top, bottom, left, right)
}

fun List<Point>.getOthersDir(point: Point): List<Point> {
    val x = point.x
    val y = point.y
    val top = this.find { it.x == x - 1 && it.y == y }
    val bottom = this.find { it.x == x + 1 && it.y == y }
    val left = this.find { it.x == x - 1 && it.y == y }
    val right = this.find { it.x == x && it.y == y + 1 }
    return listOfNotNull(top, bottom, left, right)
}