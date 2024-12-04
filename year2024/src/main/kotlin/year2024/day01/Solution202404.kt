package year2024.day01

import utils.AoCUtils
import utils.AoCUtils.test
import utils.Map2d
import utils.Map2dDirection
import utils.Point
import utils.around
import utils.parseMap

fun main() {
    val dirs = listOf(
        Map2dDirection.N,
        Map2dDirection.NE,
        Map2dDirection.E,
        Map2dDirection.SE,
        Map2dDirection.S,
        Map2dDirection.SW,
        Map2dDirection.W,
        Map2dDirection.NW
    )

    val XMAS = "XMAS"

    fun wordSearch(pos: Point, dir: Map2dDirection, nextChar: Char, map: Map2d<Char>): Long {
        if (map[pos] != nextChar) return 0
        if (map[pos] == 'S') return 1
        val next = XMAS[XMAS.indexOf(nextChar) + 1]
        return map.around(pos, listOf(dir)).map { wordSearch(it.key, dir, next, map) }.sum()
    }

    fun part1(input: String, debug: Boolean = false): Long {
        val map = parseMap(input) { i -> i }

        return map.keys.map { pos ->
            dirs.map { dir ->
                wordSearch(pos, dir, 'X', map)
            }.sum()

        }.sum().toLong()

    }

    fun part2(input: String, debug: Boolean = false): Long {
        val map = parseMap(input) { i -> i }

        return map.keys
            .filter { pos -> map[pos] == 'A' }
            .map { pos ->
                val dir1 = listOf(Map2dDirection.NW, Map2dDirection.SE)
                val dir2 = listOf(Map2dDirection.SW, Map2dDirection.NE)


                val str2 = map.around(pos, dir2).map { it.value }.sorted().joinToString()
                val str1 = map.around(pos, dir1).map { it.value }.sorted().joinToString()

//                println("str1: $str1 -- str2: $str2")
                if (str2 == "M, S" && str1 == "M, S") 1
                else 0
            }.sum().toLong()

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
    part1(input) test Pair(2521L, "part 1")

    part2(testInput) test Pair(9L, "test 2 part 2")
    part2(input) test Pair(1912L, "part 2")
    // 1964 to high
}
