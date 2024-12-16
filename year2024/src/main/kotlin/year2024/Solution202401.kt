package year2024

import utils.AoCUtils
import utils.AoCUtils.test
import kotlin.math.abs


fun main() {

    fun part1(input: String, debug: Boolean = false): Long {
        val pairs = input.lines().map {
            val split = it.split("   ")
            Pair(split.first().toLong(), split.last().toLong())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()
        return firstList.zip(secondList).sumOf { abs(it.first - it.second) }
    }

    fun part2(input: String, debug: Boolean = false): Long {
        val pairs = input.lines().map {
            val split = it.split("   ")
            Pair(split.first().toLong(), split.last().toLong())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()

        val sum = firstList.sumOf { it * secondList.count { i -> i == it } }
        return sum
    }

    val testInput = "3   4\n" +
            "4   3\n" +
            "2   5\n" +
            "1   3\n" +
            "3   9\n" +
            "3   3"

    val input = AoCUtils.readText("file202401.txt")

    part1(testInput) test Pair(11L, "test 1 part 1")
    part1(input) test Pair(3574690L, "part 1")

    part2(testInput) test Pair(31L, "test 2 part 2")
    part2(input) test Pair(22565391L, "part 2")
}