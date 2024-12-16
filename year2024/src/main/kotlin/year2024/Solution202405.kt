package year2024

import utils.AoCUtils
import utils.AoCUtils.test

fun main() {

    fun part1(input: String, debug: Boolean = false): Long {
        val rules = input.lines().filter { it.contains('|') }.map {
            val split = it.split("|")
            Pair(split.first().toLong(), split.last().toLong())
        }.groupBy { it.second }

        val rulesInverted = input.lines().filter { it.contains('|') }.map {
            val split = it.split("|")
            Pair(split.first().toLong(), split.last().toLong())
        }.groupBy { it.first }

        val orders = input.lines().filter { it.contains(',') }.map { it.split(",").map { it.toLong() }.toList() }

        val correctLists = orders.filter { list ->
            val nums = mutableListOf<Long>()
            list.forEachIndexed { i, num ->
                if (i == 0 && rules.contains(num)) {
                } else if (i == list.size - 1) {
//                } else if (!rulesInverted.contains(num)) {
//                    return@filter false
                } else {
                    rules.filter { it.key == num }.forEach {
                        // key must be before
                        if (!nums.contains(it.key)) {
                            return@filter false
                        }
                    }
                }
                nums += num
            }
            true
        }
        println(correctLists)

        return correctLists.map { correctList -> correctList[correctList.size / 2] }.sum()
    }

    fun part2(input: String, debug: Boolean = false): Long {
        return -1L
    }

    val testInput = "47|53\n" +
            "97|13\n" +
            "97|61\n" +
            "97|47\n" +
            "75|29\n" +
            "61|13\n" +
            "75|53\n" +
            "29|13\n" +
            "97|29\n" +
            "53|29\n" +
            "61|53\n" +
            "97|53\n" +
            "61|29\n" +
            "47|13\n" +
            "75|47\n" +
            "97|75\n" +
            "47|61\n" +
            "75|61\n" +
            "47|29\n" +
            "75|13\n" +
            "53|13\n" +
            "\n" +
            "75,47,61,53,29\n" +
            "97,61,53,29,13\n" +
            "75,29,13\n" +
            "75,97,47,61,53\n" +
            "61,13,29\n" +
            "97,13,75,29,47"

    val input = AoCUtils.readText("file202405.txt")

    part1(testInput) test Pair(143L, "test 1 part 1")
//    part1(input) test Pair(0L, "part 1")

    part2(testInput) test Pair(0L, "test 2 part 2")
    part2(input) test Pair(0L, "part 2")
}
