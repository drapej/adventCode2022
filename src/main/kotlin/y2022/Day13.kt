package y2022;

import utils.readText
import javax.swing.text.html.parser.Parser


private fun step1(data: String) {
    val paires = data.split("\n\n").map{ it.split("\n")}.map{it[0] to it[1]}
    val jsonParser = JSON.
paires.map{}




}

fun fromArray(str : String) : List<Any>


fun main() {

    val data = readText("src/main/resources/y2022/day13.txt")

    val datatest = """[1,1,3,1,1]
[1,1,5,1,1]

[[1],[2,3,4]]
[[1],4]

[9]
[[8,7,6]]

[[4,4],4,4]
[[4,4],4,4,4]

[7,7,7,7]
[7,7,7]

[]
[3]

[[[]]]
[[]]

[1,[2,[3,[4,[5,6,7]]]],8,9]
[1,[2,[3,[4,[5,6,0]]]],8,9]"""

    step1(datatest)

}






