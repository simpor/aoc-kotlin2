package year2024


import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import utils.AoCUtils

val input1 = """
                ###############
                #.......#....E#
                #.#.###.#.###.#
                #.....#.#...#.#
                #.###.#####.#.#
                #.#.#.......#.#
                #.#.#####.###.#
                #...........#.#
                ###.#.#####.#.#
                #...#.....#.#.#
                #.#.#.###.#.#.#
                #.....#...#.#.#
                #.###.#.#.#.#.#
                #S..#.....#...#
                ###############
                """.trimIndent()

val input2 = """
                #################
                #...#...#...#..E#
                #.#.#.#.#.#.#.#.#
                #.#.#.#...#...#.#
                #.#.#.#.###.#.#.#
                #...#.#.#.....#.#
                #.#.#.#.#.#####.#
                #.#...#.#.#.....#
                #.#.#####.#.###.#
                #.#.#.......#...#
                #.#.###.#####.###
                #.#.#...#.....#.#
                #.#.#.#####.###.#
                #.#.#.........#.#
                #.#.#.#########.#
                #S#.............#
                #################
                """.trimIndent()

class Test202416 : StringSpec({
    "part 1 - test 1" {
        val problem = Problem202416(input1)
        problem.partOne() shouldBe "7036"
    }
    "part 1 - test 2" {
        val problem = Problem202416(input2)
        problem.partOne() shouldBe "11048"
    }
    "part 2 - test 1" {
        val problem = Problem202416(input1)
        problem.partTwo() shouldBe "45"
    }
    "part 2 - test 2" {
        val problem = Problem202416(input2)
        problem.partTwo() shouldBe "64"
    }
    "part 1 - input" {
        val problem = Problem202416(AoCUtils.readText("file202416.txt"))
        problem.partOne() shouldBe "135512"
    }
    "part 2 - input" {
        val problem = Problem202416(AoCUtils.readText("file202416.txt"))
        problem.partTwo() shouldBe "XXX"
    }
})