package y2022;

import java.io.File


private fun step(data: String, nbre: Int) {
    loop@ for (i in 0..data.length - 1) {
        if (data.substring(i, i + nbre).toSet().size == nbre) {
            println(i + nbre)
            break@loop
        }
    }
}


fun main() {
    val data = File("src/main/resources/y2022/day6.txt").readLines().first()
    step(data, 4)
    step(data, 14)

    step("mjqjpqmgbljsphdztnvjfqwrcgsmlb", 14)
    step("bvwbjplbgvbhsrlpgdmjqwftvncz", 14)
    step("nppdvjthqldpwncqszvftbrmjlhg", 14)
    step("nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg", 14)
    step("zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw", 14)

    //  step2(data)
}




