package year2024.day01

import utils.AoCUtils
import utils.AoCUtils.test
import utils.Map2d
import utils.Map2dDirection
import utils.Point
import utils.parseMap
import utils.around

fun main() {

    val XMAS = "XMAS"

    fun wordSearch(pos: Point, nextChar: Char, map: Map2d<Char>): Long {
        if (map[pos] != nextChar) return 0
        if (map[pos] == 'S') return 1
        val next = XMAS[XMAS.indexOf(nextChar) + 1]
        return map.around(
            pos,
            listOf(
                Map2dDirection.N,
                Map2dDirection.NE,
                Map2dDirection.E,
                Map2dDirection.SE,
                Map2dDirection.S,
                Map2dDirection.SW,
                Map2dDirection.W,
                Map2dDirection.NW
            )
        )
            .map {
                wordSearch(it.key, next, map)
            }.sum()
    }

    fun part1(input: String, debug: Boolean = false): Long {

        val map = parseMap(input) { i -> i }

        return map.keys
            .map { wordSearch(it, 'X', map)
            }.sum().toLong()

    }

    fun part2(input: String, debug: Boolean = false): Long {
        return -1L
    }

    val testInput = "MMMSXXMASM\n" +
            "MSAMXMSMSA\n" +
            "AMXSXMAAMM\n" +
            "MSAMASMSMX\n" +
            "XMASAMXAMM\n" +
            "XXAMMXXAMA\n" +
            "SMSMSASXSS\n" +
            "SAXAMASAAA\n" +
            "MAMMMXMMMM\n" +
            "MXMXAXMASX"

    val input = AoCUtils.readText("file202404.txt")

    part1(testInput) test Pair(18L, "test 1 part 1")
    part1(input) test Pair(0L, "part 1")

    part2(testInput) test Pair(0L, "test 2 part 2")
    part2(input) test Pair(0L, "part 2")
}
