package y2022;

import java.io.File


// A : pierre  A
// B : papier  B
// C : ciseau  C

const val PRIORITE = ".abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

private fun step1(data: String) {
    val cadeaux = data.split("\n").flatMap {
        val sacs = it.chunked(it.length/2){it.chunked(1)}
        sacs[0].intersect(sacs[1])
    }

    println(cadeaux.map { PRIORITE.indexOf(it) }.sum())

}

private fun step2(data: String) {
    val groupes = data.split("\n").chunked(3)
    val badges = groupes.flatMap{
        val lettres = it.map { it.chunked(1)}
         lettres[0].intersect(lettres[1]).intersect(lettres[2])
    }
    println(badges.map { PRIORITE.indexOf(it) }.sum())
}

/*
private fun step2(data: String) {
    val tours = data.split("\n").map {
        val tour = it.split(" ")
        tour[0] to tour[1]
    }
    println(tours.map { score2(it.first, it.second) }.sum())
}
*/

fun main() {
    val data = File("src/main/resources/day3.txt").readBytes().decodeToString()
    step1(data)
    step2(data)




}




