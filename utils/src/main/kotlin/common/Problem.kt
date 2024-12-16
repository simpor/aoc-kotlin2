package common

abstract class Problem(val input: String, val debug: Boolean) {
    abstract fun partOne(input: String): String
    abstract fun partTwo(input: String): String
}

