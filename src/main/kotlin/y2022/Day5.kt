package y2022;

import utils.split
import java.io.File


private fun step1(data: List<String>, order : Boolean) {
    val chargement = data[0]
    val instructions = data[1].split("\n").split(" ")

    val piles = mutableMapOf<Int, MutableList<String>>()
    chargement.split("\n").dropLast(1).reversed().map {
        it.split("[").mapIndexed { index: Int, s: String ->
            s.trim().removeSuffix("]").trim()
        }
    }.forEach {
        it.forEachIndexed { index, s ->
            if (s.isNotBlank()) {
                piles.computeIfAbsent(index) { mutableListOf() }
                piles[index]!!.add(s)
            }
        }
    }

// move 3 from 2 to 9
    instructions.forEach {
        val number = it[1].toInt()
        val origin = it[3].toInt()
        val dest = it[5].toInt()

        val moved = piles[origin]!!.takeLast(number).let{
            if (order) it.reversed()
            else it
        }
        piles.computeIfPresent(origin) { _, s : MutableList<String> -> s.dropLast(number).toMutableList() }
        piles[dest]!!.addAll(moved)


    }


    println(piles.map{it.value.last()}.joinToString(""))
}

fun main() {
    val data = File("src/main/resources/y2022/day5.txt").readBytes().decodeToString().split("\n\n")
    step1(data, true)
    step1(data, false)
    //  step2(data)
}




