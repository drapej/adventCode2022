package y2021;

import utils.toIntList
import java.io.File
import kotlin.math.roundToInt


private fun compter( datas : List<String>): MutableMap<Int, Int> {
    val compteurs = mutableMapOf<Int, Int>()
     datas.map { it.chunked(1).toIntList() }.forEach {
        it.forEachIndexed { index, valeur ->
            if (valeur == 1) compteurs.put(index, compteurs.getOrDefault(index, 0) + 1)
        }
    }
    return compteurs
}
private fun step1(data: List<String>) {
    val compteurs = compter(data)
    var tauxGamma = ""
    var tauxEpsilon = ""

    val valeurs = compteurs.entries.sortedBy { it.key }
           valeurs.forEach { (_, nombre) ->
        if (nombre >= data.size / 2) {
            tauxGamma += "1"
            tauxEpsilon += "0"
        } else {
            tauxGamma += "0"
            tauxEpsilon += "1"
        }
    }
    val nombreGamma = Integer.parseInt(tauxGamma, 2)
    val nombreEpsilon = Integer.parseInt(tauxEpsilon, 2)

    println("Response : ${nombreGamma * nombreEpsilon}")
}

private fun step2(data: List<String>) {
    var index = 0
    var newData = data
    while (newData.size >1){
        newData = strings(newData, index, true)
        index++
    }
    val oxy = Integer.parseInt(newData.first(),2)

    index = 0
    newData = data
    while (newData.size >1){
        newData = strings(newData, index, false)
        index++
    }

    val dio = Integer.parseInt(newData.first(),2)

    println(oxy)
    println(dio)


    println("Response : ${oxy * dio}")
}

private fun strings(data: List<String>, index: Int, isMax: Boolean): List<String> {
    val compteurs = compter(data)
    val nombre1 = compteurs.getOrDefault(index, 0)
    val newData = if (nombre1 >= ((data.size / 2.0).roundToInt())) {
        data.filter { it.elementAt(index) ==  if (isMax) '1' else '0' }
    } else {
        data.filter { it.elementAt(index) ==  if (isMax) '0' else '1' }
    }
    return newData
}

fun main() {
   val data = File("src/main/resources/y2021/day3.txt").readLines()//readBytes().decodeToString().split("\n")
    step1(data)
    step2(data)

    //step1(File("src/main/resources/y2021/day3_test.txt").readLines())
    //step2(File("src/main/resources/y2021/day3_test.txt").readLines())
}




