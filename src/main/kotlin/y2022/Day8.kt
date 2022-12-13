package y2022;

import java.io.File


private fun step1(data: List<String>) {
    val lines = data.map { it.chunked(1).map { it.toInt() } }

    var compteur = 0
    for (x in 0..lines.size - 1) {
        for (y in 0..lines[x].size - 1) {

            val courante = lines[x][y]

            val aGauche = lines[x].filterIndexed { index, _ -> index < y }.all { it < courante }
            val aDroite = lines[x].filterIndexed { index, _ -> index > y }.all { it < courante }

            val auDessus = lines.filterIndexed { index, _ -> index < x }.all { it[y] < courante }
            val enBas = lines.filterIndexed { index, _ -> index > x }.all { it[y] < courante }
            if (aGauche || aDroite || auDessus || enBas) compteur += 1
        }
    }


    println(compteur)


}


private fun step2(data: List<String>) {
    val lines = data.map { it.chunked(1).map { it.toInt() } }

    var maxScore = 0
    for (x in 0..lines.size - 1) {
        for (y in 0..lines[x].size - 1) {

            val courante = lines[x][y]

            var aGaucheL = lines[x].filterIndexed { index, _ -> index < y }
            val gaucheFirst = aGaucheL.indexOfLast { it >= courante }
            val aGauche = aGaucheL.filterIndexed { index, _ ->
                if (gaucheFirst == -1) true else  gaucheFirst <= index
            }.size

            val aDroiteL = lines[x].filterIndexed { index, _ -> index > y }
            val aDroitFirst = aDroiteL.indexOfFirst { it >= courante }
            val aDroite = aDroiteL.filterIndexed { index, _ ->
                if (aDroitFirst == -1) true else index <= aDroitFirst
            }.size


            val auDessusL = lines.filterIndexed { index, _ -> index < x }
            val dessusFirst = auDessusL.indexOfLast { it[y] >= courante }
            val auDessus = auDessusL.filterIndexed { index, _ ->
                if (dessusFirst == -1) true else  dessusFirst <= index
            }.size

            val enBasL = lines.filterIndexed { index, _ -> index > x }
            val basFirst = enBasL.indexOfFirst { it[y] >= courante }
            val enBas = enBasL.filterIndexed{index, _ ->
                if (basFirst == -1) true else index <= basFirst
            }.size

            maxScore = maxOf(maxScore, aDroite * aGauche * auDessus * enBas)
        }
    }


    println(maxScore)


}

fun main() {
    val data = File("src/main/resources/y2022/day8.txt").readLines()
    val datatest = """30373
25512
65332
33549
35390""".split("\n")

    step1(data)
    step2(data)


}




