
fun String.toInts() : List<Int> {
    return Regex("-?\\d+").findAll(this).map{it.value.toInt()}.toList()
}

fun String.toLongs() : List<Long> {
    return Regex("-?\\d+").findAll(this).map{it.value.toLong()}.toList()
}

fun String.next() = this[0].inc().toString()
fun String.prev() = this[0].dec().toString()



