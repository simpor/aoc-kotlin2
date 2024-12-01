package year2024.day01

import AoCUtils.readLines
import kotlin.math.abs

fun main() {
    Solution202401().solveFirst()
    Solution202401().solveSecond()
}


class Solution202401 {
    fun solveFirst() {
        val lines = readLines("file202401.txt")

        val pairs = lines.map {
            val split = it.split("   ")
            Pair(split.first().toInt(), split.last().toInt())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()

        val sum = firstList.mapIndexed { index, i -> abs(i - secondList[index]) }.sum()

        println(sum)

    }
    fun solveSecond() {
        val lines = readLines("file202401.txt")

        val pairs = lines.map {
            val split = it.split("   ")
            Pair(split.first().toInt(), split.last().toInt())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()

        val sum = firstList.map { it * secondList.filter { i -> i == it }.count() }.sum()

        println(sum)

    }
}