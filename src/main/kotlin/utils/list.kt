package utils



/**
 * Transforme un string en list de Int
 */
fun String.toIntList(separator: String? = null) = if (separator == null) this.chunked(1).toIntList() else this.split(separator).toIntList()


/**
 * Transforme liste de String en list Int
 */
fun Collection<String>.toIntList() = this.map { it.toInt() }


// Split de List String
fun <R> Collection<String>.split(separator: String = "\n", transformValue: (String) -> R ) = this.map {
    val values = it.split(separator)
    values.map { value -> transformValue(value) }
}
fun Collection<String>.split(separator: String) = this.map { it.split(separator) }


fun Set<Any>.isSame(v : Set<Any>) = this.containsAll(v) && v.containsAll(this)






