package year2024

import common.Problem
import utils.Point
import utils.parseMap
import java.util.*

/**
 * --- Day 16: Reindeer Maze ---
 * It's time again for the Reindeer Olympics! This year, the big event is the Reindeer Maze, where the Reindeer compete for the lowest score.
 *
 * You and The Historians arrive to search for the Chief right as the event is about to start. It wouldn't hurt to watch a little, right?
 *
 * The Reindeer start on the Start Tile (marked S) facing East and need to reach the End Tile (marked E). They can move forward one tile at a time (increasing their score by 1 point), but never into a wall (#). They can also rotate clockwise or counterclockwise 90 degrees at a time (increasing their score by 1000 points).
 *
 * To figure out the best place to sit, you start by grabbing a map (your puzzle input) from a nearby kiosk. For example:
 *
 * ###############
 * #.......#....E#
 * #.#.###.#.###.#
 * #.....#.#...#.#
 * #.###.#####.#.#
 * #.#.#.......#.#
 * #.#.#####.###.#
 * #...........#.#
 * ###.#.#####.#.#
 * #...#.....#.#.#
 * #.#.#.###.#.#.#
 * #.....#...#.#.#
 * #.###.#.#.#.#.#
 * #S..#.....#...#
 * ###############
 * There are many paths through this maze, but taking any of the best paths would incur a score of only 7036.
 * This can be achieved by taking a total of 36 steps forward and turning 90 degrees a total of 7 times:
 *
 *
 * ###############
 * #.......#....E#
 * #.#.###.#.###^#
 * #.....#.#...#^#
 * #.###.#####.#^#
 * #.#.#.......#^#
 * #.#.#####.###^#
 * #..>>>>>>>>v#^#
 * ###^#.#####v#^#
 * #>>^#.....#v#^#
 * #^#.#.###.#v#^#
 * #^....#...#v#^#
 * #^###.#.#.#v#^#
 * #S..#.....#>>^#
 * ###############
 * Here's a second example:
 *
 * #################
 * #...#...#...#..E#
 * #.#.#.#.#.#.#.#.#
 * #.#.#.#...#...#.#
 * #.#.#.#.###.#.#.#
 * #...#.#.#.....#.#
 * #.#.#.#.#.#####.#
 * #.#...#.#.#.....#
 * #.#.#####.#.###.#
 * #.#.#.......#...#
 * #.#.###.#####.###
 * #.#.#...#.....#.#
 * #.#.#.#####.###.#
 * #.#.#.........#.#
 * #.#.#.#########.#
 * #S#.............#
 * #################
 * In this maze, the best paths cost 11048 points; following one such path would look like this:
 *
 * #################
 * #...#...#...#..E#
 * #.#.#.#.#.#.#.#^#
 * #.#.#.#...#...#^#
 * #.#.#.#.###.#.#^#
 * #>>v#.#.#.....#^#
 * #^#v#.#.#.#####^#
 * #^#v..#.#.#>>>>^#
 * #^#v#####.#^###.#
 * #^#v#..>>>>^#...#
 * #^#v###^#####.###
 * #^#v#>>^#.....#.#
 * #^#v#^#####.###.#
 * #^#v#^........#.#
 * #^#v#^#########.#
 * #S#>>^..........#
 * #################
 * Note that the path shown above includes one 90 degree turn as the very first move,
 * rotating the Reindeer from facing East to facing North.
 *
 * Analyze your map carefully. What is the lowest score a Reindeer could possibly get?
 */

enum class MapObject {
    BLOCK, EMPTY, START, END;
}

//data class Position(val x: Int, val y: Int)
enum class Direction { NORTH, EAST, SOUTH, WEST }

data class State(
    val position: Point,
    val direction: Direction,
    val score: Int = 0,
    val pos:List<State> = emptyList(),
)


class Problem202416(input: String, debug: Boolean = false) : Problem(input, debug) {
    override fun partOne(): String {
        val map = parseMap(input) { i ->
            when (i) {
                '#' -> MapObject.BLOCK
                '.' -> MapObject.EMPTY
                'S' -> MapObject.START
                'E' -> MapObject.END
                else -> throw IllegalArgumentException("Unknown character $i")
            }
        }

        // Find start and end positions
        val start = map.entries.first { it.value == MapObject.START }.key
        val end = map.entries.first { it.value == MapObject.END }.key

        // Use priority queue for Dijkstra's algorithm
        val queue = PriorityQueue<State>(compareBy { it.score })
        val visited = mutableSetOf<Pair<Point, Direction>>()

        // Start facing EAST as per problem description
        queue.add(State(start, Direction.EAST))

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.position == end) {
                return current.score.toString()
            }

            if (!visited.add(current.position to current.direction)) {
                continue
            }

            // Try moving forward
            val nextPos = when (current.direction) {
                Direction.NORTH -> Point(current.position.x, current.position.y - 1)
                Direction.EAST -> Point(current.position.x + 1, current.position.y)
                Direction.SOUTH -> Point(current.position.x, current.position.y + 1)
                Direction.WEST -> Point(current.position.x - 1, current.position.y)
            }

            if (map[nextPos] != MapObject.BLOCK) {
                queue.add(State(nextPos, current.direction, current.score + 1))
            }

            // Try rotating left and right (costs 1000 points each)
            val leftDir = when (current.direction) {
                Direction.NORTH -> Direction.WEST
                Direction.EAST -> Direction.NORTH
                Direction.SOUTH -> Direction.EAST
                Direction.WEST -> Direction.SOUTH
            }
            queue.add(State(current.position, leftDir, current.score + 1000))

            val rightDir = when (current.direction) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
            }
            queue.add(State(current.position, rightDir, current.score + 1000))
        }

        return ""
    }


    override fun partTwo(): String {
        val map = parseMap(input) { i ->
            when (i) {
                '#' -> MapObject.BLOCK
                '.' -> MapObject.EMPTY
                'S' -> MapObject.START
                'E' -> MapObject.END
                else -> throw IllegalArgumentException("Unknown character $i")
            }
        }
        // Find start and end positions
        val start = map.entries.first { it.value == MapObject.START }.key
        val end = map.entries.first { it.value == MapObject.END }.key
        // Use priority queue for Dijkstra's algorithm
        // Find minimum score first
        val queue = PriorityQueue<State>(compareBy { it.score })
        val visited = mutableSetOf<Pair<Point, Direction>>()
        queue.add(State(Point(start.x, start.y), Direction.EAST))
        // For part 2, try all initial directions
        var minScore = Int.MAX_VALUE
        val optimalPaths = mutableSetOf<Point>()

        val paths = mutableListOf<State>()

        while (queue.isNotEmpty()) {
            val current = queue.poll()

            if (current.score > minScore) continue

            if (current.position == end) {
                paths.add(current);
                continue
            }


            if (!visited.add(current.position to current.direction)) continue

            // Add current position to optimal paths
            optimalPaths.add(current.position)

            // Try moving forward
            val nextPos = when (current.direction) {
                Direction.NORTH -> Point(current.position.x, current.position.y - 1)
                Direction.EAST -> Point(current.position.x + 1, current.position.y)
                Direction.SOUTH -> Point(current.position.x, current.position.y + 1)
                Direction.WEST -> Point(current.position.x - 1, current.position.y)
            }

            if (map[nextPos] != MapObject.BLOCK) {
                queue.add(State(nextPos, current.direction, current.score + 1, current.pos + current))
            }

            // Try rotations
            val leftDir = when (current.direction) {
                Direction.NORTH -> Direction.WEST
                Direction.EAST -> Direction.NORTH
                Direction.SOUTH -> Direction.EAST
                Direction.WEST -> Direction.SOUTH
            }
            queue.add(State(current.position, leftDir, current.score + 1000, current.pos + current))

            val rightDir = when (current.direction) {
                Direction.NORTH -> Direction.EAST
                Direction.EAST -> Direction.SOUTH
                Direction.SOUTH -> Direction.WEST
                Direction.WEST -> Direction.NORTH
            }
            queue.add(State(current.position, rightDir, current.score + 1000, current.pos + current))
        }


        val sorted = paths.sortedBy { it.score }.reversed().first()

        return paths.filter { it.score == sorted.score }.map { it.pos }.flatten().map { it.position }.toSet().count().toString()

    }
}



