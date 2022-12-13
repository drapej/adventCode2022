package y2021;

import utils.readLines
import utils.toIntList
import java.io.File
import java.lang.Math.abs
import kotlin.math.roundToInt
import kotlin.math.truncate

private fun step1(data: List<Int>) {
    val chunks = data.sorted().chunked(data.size / 2)
    val cible = chunks[0].last()
    println(data.map { abs(it - cible) }.sum())
}

private fun step2(data: List<Int>) {
    println(data.average())
    val cible = data.average().roundToInt()
    val valeur =
        data.map {
            val x = abs(it - cible)
            x * (x + 1) / 2
        }.sum()
    println(valeur)

    val cible2 = truncate(data.average()).toInt()
    val valeur2 =
        data.map {
            val x = abs(it - cible2)
            x * (x + 1) / 2
        }.sum()
    println(valeur2)


}

//1 +2 + 3 +4 +5 =>15
// x + x -1 + x -2 + x-3 +x-4 +x-5


fun main() {
    val data = readLines("src/main/resources/y2021/day7.txt").first().toIntList(",")//readBytes().decodeToString().split("\n")
    val datatest = "16,1,2,0,4,2,7,1,2,14".toIntList(",")
   // step1(datatest)
   // step2(datatest)

    step1(data)
    //step2(data)


}
