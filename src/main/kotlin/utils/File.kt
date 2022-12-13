package utils

import java.io.File


fun readLines(path : String) = File(path).readLines()
fun readText(path : String) = File(path).readText()
