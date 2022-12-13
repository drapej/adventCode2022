package y2021;

import utils.split
import java.io.File


private fun step1(data: String) {
    var (tirage, grilles) = data.split("\n\n").split("\n").partition { it.size == 1 }

    val tirage2 = tirage.first().first().split(",")

    val grilles2 = grilles.map { it.map { line -> line.trim().replace("  ", " ").split(" ").toMutableList() } }

    var found = false
    loop@ tirage2.forEach { valeur ->

        grille@ grilles2.forEach { grille ->
            if (found) return@forEach
            grille.forEach { line ->
                line.replaceAll { if (it.equals(valeur)) "" else it }
            }

            found = checkComplet(grille, valeur)
        }
    }
}

private fun checkComplet(
    grille: List<MutableList<String>>,
    valeur: String
): Boolean {
    if (grille.any { it.all { it.isEmpty() } }) {
        val sum = grille.sumOf { it.sumOf { if (it.isBlank()) 0 else it.toInt() } }
        println("Grille rempli ligne ${sum * valeur.toInt()}")
        return true
    }
    for (i in 0..grille.first().size - 1) {
        if (grille.all { it[i].isEmpty() }) {
            val sum = grille.sumOf { it.sumOf { if (it.isBlank()) 0 else it.toInt() } }
            println("Grille rempli ligne ${sum * valeur.toInt()}")
            return true
        }
    }

    return false
}


private fun step2(data: String) {
    var (tirage, grilles) = data.split("\n\n").split("\n").partition { it.size == 1 }

    val tirage2 = tirage.first().first().split(",")

    var grilles2 =
        grilles.map { it.map { line -> line.trim().replace("  ", " ").split(" ").toMutableList() }.toMutableList() }

    loop@ tirage2.forEach { valeur ->

        grille@ grilles2.forEach { grille ->
            grille.forEach { line ->
                line.replaceAll { if (it.equals(valeur)) "" else it }
            }
            if (grille.any { it.all { it.isEmpty() } }) {
                if (grilles2.size > 1)
                    grille.clear()
                else
                    checkComplet(grille, valeur)
            } else {
                for (i in 0..grille.first().size - 1) {
                    if (grille.all { it[i].isEmpty() }) {
                        if (grilles2.size > 1)
                            grille.clear()
                        else
                            checkComplet(grille, valeur)
                    }
                }
            }
        }
        grilles2 = grilles2.filter { it.isNotEmpty() }
    }
}

fun main() {
    val data = File("src/main/resources/y2021/day4.txt").readBytes().decodeToString()//.split("\n")
    step1(data)
    step2(data)

}




