package year2024

import utils.AoCUtils
import utils.AoCUtils.test
import utils.Map2d
import utils.Map2dDirection
import utils.Point
import utils.parseMap

fun main() {


    fun debugPrint(map: Map2d<Char>) {
        val keys = map.keys.toList()
        val xMax = keys.map { it.x }.max()
        val xMin = keys.map { it.x }.min()
        val yMax = keys.map { it.y }.max()
        val yMin = keys.map { it.y }.min()

        for (y in yMin..yMax) {
            for (x in xMin..xMax) {
                print(map[Point(x, y)] ?: " ")
            }
            println()
        }

    }

    fun part1(input: String, debug: Boolean = false): Long {
        val map = parseMap(input) { i -> i }

        var knight = map.filter { it.value == '^' }.toList().first().first
        var currentDir = Map2dDirection.N
        while (map.containsKey(knight)) {
            map[knight] = 'X'
            val newPos = when (currentDir) {
                Map2dDirection.N -> Point(knight.x, knight.y - 1)
                Map2dDirection.S -> Point(knight.x, knight.y + 1)
                Map2dDirection.W -> Point(knight.x - 1, knight.y)
                Map2dDirection.E -> Point(knight.x + 1, knight.y)
                else -> throw Exception("Unknown direction")
            }

            if (map[newPos] == '#') {
                currentDir = when (currentDir) {
                    Map2dDirection.N -> Map2dDirection.E
                    Map2dDirection.E -> Map2dDirection.S
                    Map2dDirection.S -> Map2dDirection.W
                    Map2dDirection.W -> Map2dDirection.N
                    else -> throw Exception("Unknown direction")
                }
                knight = when (currentDir) {
                    Map2dDirection.N -> Point(knight.x, knight.y - 1)
                    Map2dDirection.S -> Point(knight.x, knight.y + 1)
                    Map2dDirection.W -> Point(knight.x - 1, knight.y)
                    Map2dDirection.E -> Point(knight.x + 1, knight.y)
                    else -> throw Exception("Unknown direction")
                }
            } else {
                knight = newPos
            }

        }
        debugPrint(map)

        return map.values.count { it == 'X' }.toLong()
    }

    fun part2(input: String, debug: Boolean = false): Long {
        return -1L
    }

    val testInput = "....#.....\n" +
            ".........#\n" +
            "..........\n" +
            "..#.......\n" +
            ".......#..\n" +
            "..........\n" +
            ".#..^.....\n" +
            "........#.\n" +
            "#.........\n" +
            "......#..."

    val input = AoCUtils.readText("file202406.txt")

    part1(testInput) test Pair(41L, "test 1 part 1")
    part1(input) test Pair(0L, "part 1")

    part2(testInput) test Pair(0L, "test 2 part 2")
    part2(input) test Pair(0L, "part 2")
}
