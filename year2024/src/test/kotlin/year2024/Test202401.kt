package year2024


import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import utils.AoCUtils


class Test202401 : StringSpec({

    "part 1 - test 1" {
        val problem = Problem202401(testInput)
        problem.partOne() shouldBe "11"
    }
    "part 2 - test 1" {
        val problem = Problem202401(testInput)
        problem.partTwo() shouldBe "31"
    }

    "part 1 - input" {
        val problem = Problem202401(AoCUtils.readText(inputFile))
        problem.partOne() shouldBe "3574690"
    }

    "part 2 - input" {
        val problem = Problem202401(AoCUtils.readText(inputFile))
        problem.partTwo() shouldBe "22565391"
    }
})

private val inputFile = "file202401.txt"

private val testInput = "3   4\n" +
        "4   3\n" +
        "2   5\n" +
        "1   3\n" +
        "3   9\n" +
        "3   3"
