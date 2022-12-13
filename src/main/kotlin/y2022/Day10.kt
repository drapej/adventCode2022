package y2022;

import utils.Point
import utils.split
import java.io.File

val mesures = listOf(20, 40 , 60,80 , 100, 120, 140, 180, 220)

private fun step1(data: List<String>) {
    val instrs = data.split(" ").map {
        when (it[0]) {
            "noop" -> 1
            else -> 2
        } to it.getOrElse(1) { "0" }.toInt()
    }

    val cyclesAConsiderer = mutableListOf<Point>()

    instrs.fold(Point(0, 1)) { cycles, instr ->

        (1..instr.first).forEach {
            cycles.addX()

            if (cycles.x in mesures) {
                cyclesAConsiderer.add(cycles.copy())
            }
            if (it == 2) {
                cycles.addY(instr.second)
            }


        }

        cycles
    }

 /*   println(cyclesAConsiderer)
    println(cyclesAConsiderer.sumOf { it.x * it.y })*/

}


val crts = listOf(40, 80, 120, 160, 200, 240)

private fun step2(data: List<String>) {
    val instrs = data.split(" ").map {
        when (it[0]) {
            "noop" -> 1
            else -> 2
        } to it.getOrElse(1) { "0" }.toInt()
    }
    val cyclesAConsiderer = mutableListOf<Point>()

    instrs.fold(Point(0, 1)) { data, instr ->

        (1..instr.first).forEach {
            if (data.x.mod(40) in (data.y-1 .. data.y+1)){
                print("#")
            } else {
                print(".")
            }
            data.addX()


            if (data.x in mesures) {
                cyclesAConsiderer.add(data.copy())
            }

            if (it == 2) {
                data.addY(instr.second)
            }

            if (data.x in crts){
                println()
            }

        }

        data
    }
    println(cyclesAConsiderer)


}


fun main() {
    val data = File("src/main/resources/y2022/day10.txt").readLines()
  //  val datatest = File("src/main/resources/y2022/day10_test.txt").readLines()
  //  step1(data)
    step2(data)
}