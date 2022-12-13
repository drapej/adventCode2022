package y2022;

import java.io.File


data class Repertoire(
    val name: String,
    var filesSize: Int = 0,
    val dir: MutableMap<String, Repertoire> = mutableMapOf()
) {
    fun addFile(size: Int) {
        filesSize += size
    }

    fun getTotalSize(): Int = filesSize + dir.values.sumOf { it.getTotalSize() }

}

private fun step(data: List<String>): MutableList<Repertoire> {

    val repertoireRacine = Repertoire("/")
    var currentRepertoire: Repertoire = Repertoire(" ")
    val listRepertoire = mutableListOf<Repertoire>()
    val all = mutableListOf(repertoireRacine)
    data.forEachIndexed { index, cmd ->
        val inst = cmd.split(" ")

        if (inst[0] == "$" && inst[1] == "cd") {
            if (inst[2] == "..") {
                listRepertoire.removeLast()
                currentRepertoire = listRepertoire.last()
            } else if (inst[2] == "/") {
                currentRepertoire = repertoireRacine
                listRepertoire.add(repertoireRacine)
            } else {
                currentRepertoire =
                    currentRepertoire.dir[inst[2]] ?: throw Exception("comment c'est possible ?") //Repertoire(inst[2])
                listRepertoire.add(currentRepertoire)
            }
        } else if (inst[0] == "$" && inst[1] == "ls") {

        } else if (inst[0] == "dir") {
            val newRep = Repertoire(inst[1])
            all.add(newRep)
            currentRepertoire.dir.put(newRep.name, newRep)
        } else {
            // on ajout les fichiers
            currentRepertoire.addFile(inst[0].toInt())
        }
    }



    println(all.filter { it.getTotalSize() <= 100_000 }.sumOf { it.getTotalSize() })
    return all
}


fun main() {
    val data = File("src/main/resources/y2022/day7.txt").readLines()
    val reps = step(data)

    val totalSize = reps.first().getTotalSize()

    val MAX = 70000000
    val needed = 30000000

    val free = MAX - totalSize
    val toRemove = needed - free
    val rep = reps.sortedBy { it.getTotalSize() }.filter { it.getTotalSize() >= toRemove }.first()
    println(rep.getTotalSize())
}




