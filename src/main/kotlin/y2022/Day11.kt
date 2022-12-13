package y2022;

import toInts
import toLongs
import java.io.File
import java.math.BigInteger


private fun step(data: String, nbre : Int, op : (Long, List<Monkey>)-> Long) {

    val monkeys = data.split("\n\n").map { monkey ->
        val it = monkey.split("\n")
        val items = it[1].toLongs().toMutableList()
        val operation = it[2].split(" = ").last().split(" ")
        val test = Test(it[3].toLongs().first(), it[4].toInts().first(), it[5].toInts().first())
        Monkey(items, operation, test)
    }

    repeat(nbre) {
        monkeys.forEach { monkey ->
            monkey.items.forEach { item ->
                val newItem = op(monkey.compute(item), monkeys)
                if (newItem.mod(monkey.test.modulo) == 0L){
                    monkeys[monkey.test.ifTrue].addItem(newItem)
                } else {
                    monkeys[monkey.test.ifFalse].addItem(newItem)
                }
            }
            monkey.nbreItems += monkey.items.size
            monkey.items.clear()
        }
    }

    val max2 = monkeys.map {it.nbreItems  }.sortedDescending().take(2)
    println(max2[0]*max2[1])

}

fun main() {
    val data = File("src/main/resources/y2022/day11.txt").readText()
    step(data, 20) { value, monkeys -> value / 3 }
    step(data, 10000) { value, monkeys ->
        val modulo =
            monkeys.map { it.test.modulo.toBigInteger() }.fold(BigInteger.ONE) { acc, i -> (acc * i) / acc.gcd(i) }
                .toLong()
        value % modulo
    }
}


data class Test(val modulo: Long, val ifTrue: Int, val ifFalse: Int)
data class Monkey(val items: MutableList<Long>, val operation: List<String>, val test: Test, var nbreItems : Long = 0) {


    fun compute(item: Long): Long {
        val ope: (Long) -> Long = { number ->
            when (operation[1]) {
                "*" -> item * number
                "+" -> item + number
                else -> throw Exception("not know operation")
            }
        }
        val number = if (operation[2] == "old") item else operation[2].toLong()
        return ope(number)
    }

    fun addItem(newItem: Long) {
        this.items.add(newItem)
    }
}

