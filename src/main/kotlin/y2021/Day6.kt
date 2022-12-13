package y2021;

import utils.readLines
import utils.toIntList
import java.io.File

private fun step1(data: List<Int>, nbreJour: Int) {

    val poissons = data.toMutableList()

    val totalStart = System.currentTimeMillis()
    for (i in 1..nbreJour) {
        val start = System.currentTimeMillis()
        poissons.replaceAll { it - 1 }
        val nbre_reinit = poissons.count { it == -1 }
        poissons.addAll((1..nbre_reinit).map { 8 })
        poissons.replaceAll { if (it == -1) 6 else it }
    }

    println(poissons.size)

}


private fun step2(data: List<Int>, nbreJour: Int) {

    val times = (0..8).map { 0L }.toMutableList()
    data.forEach { times[it] += 1L }
    repeat(nbreJour) {
        val nbre0 = times.removeFirst()
        times[6] += nbre0
        times.add(nbre0)
    }
    println(times.sum())

}

fun main() {


    val data = readLines("src/main/resources/y2021/day6.txt").first().toIntList(",")
    val datatest = "3,4,3,1,2".toIntList(",")
    step2(data, 80)
    step2(data, 256)

    //step1(File("src/main/resources/y2021/day3_test.txt").readLines())
    //step2(File("src/main/resources/y2021/day3_test.txt").readLines())
}





