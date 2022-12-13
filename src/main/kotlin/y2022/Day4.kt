package y2022;

import utils.split
import utils.toIntList
import java.io.File


private fun step1(data: List<String>) {
    val plages = data.split(",") {
        it.toIntList("-").let { it[0]..it[1] }
    }
    println(plages.count { (x, y) -> x.all { it in y } || y.all { it in x } })
    println(plages.count { (x, y) -> x.any { it in y } || y.any { it in x } })
}

fun main() {
    val data = File("src/main/resources/y2022/day4.txt").readLines()//readBytes().decodeToString().split("\n")
    step1(data)
    //  step2(data)
}




