package common

abstract class Problem(val input: String, val debug: Boolean) {
    abstract fun partOne(): String
    abstract fun partTwo(): String
}

