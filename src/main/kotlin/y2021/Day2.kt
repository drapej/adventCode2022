package y2021;

import utils.split
import java.io.File


private fun step1(data: List<String>) {
    val valeurs = data.split(" ").map{it[0] to it[1].toInt()}
    var profondeur = 0
    var horizontal = 0

    valeurs.forEach { (action, valeur) ->
        when(action){
            "forward" -> horizontal+=valeur
            "down" -> profondeur+=valeur
            "up" -> profondeur-=valeur
        }
    }
    println("Response : ${profondeur*horizontal}")
}

private fun step2(data: List<String>) {
    val valeurs = data.split(" ").map{it[0] to it[1].toInt()}
    var profondeur = 0
    var horizontal = 0
    var aim = 0

    valeurs.forEach { (action, valeur) ->
        when(action){
            "forward" -> {
                horizontal+=valeur
                profondeur+=aim*valeur
            }
            "down" -> aim+=valeur
            "up" -> aim-=valeur
        }
    }
    println("Response : ${profondeur*horizontal}")
}

fun main() {
    val data = File("src/main/resources/y2021/day2.txt").readLines()//readBytes().decodeToString().split("\n")
    step1(data)
      step2(data)
}




