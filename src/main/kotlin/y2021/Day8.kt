package y2021;

import utils.isSame
import utils.split
import java.io.File

private val mapChiffre = mapOf(
    1 to ("cf").chunked(1),
    7 to ("acf").chunked(1),
    4 to ("bcdf").chunked(1),
    8 to ("abcdefg").chunked(1),

    2 to ("acdeg").chunked(1),
    3 to ("acdfg").chunked(1),
    5 to ("abdfg").chunked(1),
    0 to ("abcefg").chunked(1),
    6 to ("abdefg").chunked(1),
    9 to ("abcdfg").chunked(1),
)


private fun step1(data: List<String>) {
    val datas = data.split("|").map { it[0].split(" ") to it[1].split(" ") }
    val uniqueCase =
        mapChiffre.values.groupBy { it.size }.entries.filter { it.value.size == 1 }.map { it.key to it.value }
    val allUniquesNombre = datas.map { it.second.filter { it.length in uniqueCase.map { it.first } }.size }.sum()
    println(allUniquesNombre)

}

data class Data(val entrees: List<String>, val sorties: List<String>)

private fun step2(data: List<String>) {
    val datas = data.split("|").map { it[0].trim().split(" ") to it[1].split(" ") }

   val sommes =  datas.map { (entrees, sorties) ->
        val un = entrees.first { it.length == 2 }.toSet()
        val quatre = entrees.first { it.length == 4 }.toSet()
        val sept = entrees.first { it.length == 3 }.toSet()
        val huit = entrees.first { it.length == 7 }.toSet()

        val size5 = entrees.filter { it.length == 5 }.map { it.toSet() }
        val size6 = entrees.filter { it.length == 6 }.map { it.toSet() }

        val six = size6.first { it.intersect(un).size == 1 }
        val trois = size5.first { it.containsAll(un) }
        val neuf = size6.first { it.containsAll(quatre) }
        val zero = size6.first { !it.containsAll(six) && !it.containsAll(neuf) }

        val leftBE = (huit - neuf).first()

       val cinq = size5.first { six.intersect(it).size == it.size  }
       val deux = size5.first {!it.containsAll(cinq) && !it.containsAll(trois)  }

        sorties.map {
            val list = it.toSet()
            when {
                list.isSame(un) -> 1
                list.isSame(deux) -> 2
                list.isSame(trois) -> 3
                list.isSame(quatre) -> 4
                list.isSame(cinq) -> 5
                list.isSame(six) -> 6
                list.isSame(sept) -> 7
                list.isSame(huit) -> 8
                list.isSame(neuf) -> 9
                list.isSame(zero) -> 0
                else -> 0
            }
        }.joinToString("").toInt()
    }.sum()

    println(sommes)
    //1051087

}


fun main() {
    val data = File("src/main/resources/y2021/day8.txt").readLines()

    val datatest = """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb |fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec |fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef |cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega |efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga |gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf |gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf |cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd |ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg |gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc |fgae cfgab fg bagce""".split("\n")
    // step1(datatest)
    // step2(datatest)


    println("azef".chunked(1) - "aze".chunked(1))


    step1(data)


    val datatest2 = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab |cdfeb fcadb cdfeb cdbaf".split("\n")
    step2(data)


}