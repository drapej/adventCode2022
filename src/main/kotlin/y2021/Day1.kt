package y2021;

import utils.toIntList
import java.io.File


private fun step1(data: List<String>) {
    val valeurs = data.toIntList()
    val ite = valeurs.iterator()
    var previous = ite.next()
    var count = 0
    while (ite.hasNext()){
        val current = ite.next()
        if (previous<current) count ++
        previous = current
    }
    println(count)
}

private fun step2(data: List<String>) {
    val valeurs = data.toIntList()
    var count = 0
    valeurs.forEachIndexed { index, valeur ->
        if (index > 0 && index < valeurs.size-2) {
            val previous = valeurs.subList(index - 1, index + 2).sum()
            val current = valeurs.subList(index, index + 3).sum()
            if (previous < current) count++
        }
    }

    println(count)
}


fun main() {
    val data = File("src/main/resources/y2021/day1.txt").readLines()//readBytes().decodeToString().split("\n")
    step1(data)
      step2(data)
}




