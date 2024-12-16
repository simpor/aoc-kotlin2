package year2024

import common.Problem
import kotlin.math.abs


class Problem202401(input: String, debug: Boolean = false) : Problem(input, debug) {
    override fun partOne(): String {
        val pairs = input.lines().map {
            val split = it.split("   ")
            Pair(split.first().toLong(), split.last().toLong())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()
        return firstList.zip(secondList).sumOf { abs(it.first - it.second) }.toString()
    }

    override fun partTwo(): String {
        val pairs = input.lines().map {
            val split = it.split("   ")
            Pair(split.first().toLong(), split.last().toLong())
        }

        val firstList = pairs.map { it.first }.sorted()
        val secondList = pairs.map { it.second }.sorted()

        val sum = firstList.sumOf { it * secondList.count { i -> i == it } }
        return sum.toString()
    }
}