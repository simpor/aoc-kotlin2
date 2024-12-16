package year2024

import utils.AoCUtils
import utils.AoCUtils.test

fun main() {

    fun part1(input: String, debug: Boolean = false): Long {
        return -1L
    }

    fun part2(input: String, debug: Boolean = false): Long {
        return -1L
    }

    val testInput = "190: 10 19\n" +
            "3267: 81 40 27\n" +
            "83: 17 5\n" +
            "156: 15 6\n" +
            "7290: 6 8 6 15\n" +
            "161011: 16 10 13\n" +
            "192: 17 8 14\n" +
            "21037: 9 7 18 13\n" +
            "292: 11 6 16 20"

    val input = AoCUtils.readText("file202407.txt")

    part1(testInput) test Pair(0L, "test 1 part 1")
    part1(input) test Pair(0L, "part 1")

    part2(testInput) test Pair(0L, "test 2 part 2")
    part2(input) test Pair(0L, "part 2")
}
