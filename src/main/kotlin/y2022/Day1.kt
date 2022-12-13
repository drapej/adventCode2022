package y2022;

import java.io.File

private fun step1(data : String) {
    val max = data.split("\n\n").map { it.split("\n").sumOf { it.toLong() } }.max()
    println(max)
}

private fun step2(data : String) {
    val max = data.split("\n\n").map { it.split("\n").sumOf { it.toLong() } }.sortedDescending().subList(0,3)
    println(max + " " + max.sum())
}

fun main() {
    val data = File("src/main/resources/day1.txt").readBytes().decodeToString()
    step1(data)
    step2(data)
}