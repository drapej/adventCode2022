package y2022;
/*
private fun step2(data: String) {
    val tours = data.split("\n").map {
        val tour = it.split(" ")
        tour[0] to tour[1]
    }
    println(tours.map { score2(it.first, it.second) }.sum())
}
*/
/*
private fun step2(data: String) {
    val tours = data.split("\n").map {
        val tour = it.split(" ")
        tour[0] to tour[1]
    }
    println(tours.map { score2(it.first, it.second) }.sum())
}
*/

import java.io.File


// A : pierre  A
// B : papier  B
// C : ciseau  C


private fun step1(data: String) {
    val tours = data.split("\n").map {
        val tour = it.split(" ")
        tour[0] to tour[1]
    }
    println(tours.map { score(it.first, it.second) }.sum())
}

private fun score(other: String, me: String): Int {
    val indexOther = "ABC".indexOf(other)
    val indexMe = "XYZ".indexOf(me)
    val resultat = indexMe - indexOther
    var score = indexMe + 1
    if(resultat.mod(3) == 1) {
        score += 6
    } else if(resultat.mod(3) == 0) {
        score += 3
    }
    return score
}



private fun step2(data: String) {
    val tours = data.split("\n").map {
        val tour = it.split(" ")
        tour[0] to tour[1]
    }
    println(tours.map { score2(it.first, it.second) }.sum())
}


//: Xsignifie que vous devez perdre, Ysignifie que vous devez terminer le tour par un match nul et Zsignifie que vous devez gagner. Bonne chance !"

private fun score2(other: String, me: String): Int {
    val indexOther = "ABC".indexOf(other)
    var score = 0
    when(me){
        "X"-> score +=(indexOther-1).mod(3) +1
        "Y"-> score += 3 + indexOther +1
        "Z" -> score += 6 + (indexOther +1 ).mod(3) +1
    }
    return score
}


fun main() {
    val data = File("src/main/resources/day2.txt").readBytes().decodeToString()
    step1(data)
    step2(data)

    // Y > A
    // Z > B
    // X > C

    println(score("A","X") == 4)
    println(score("B","Y") == 5)
    println(score("C","Z") == 6)

    println(score("A","Y") == 8)
    println(score("A","Z") == 3)

    println(score("B","X") == 1)
    println(score("B","Z") == 9)

    println(score("C","X") == 7)
    println(score("C","Y") == 2)



}




