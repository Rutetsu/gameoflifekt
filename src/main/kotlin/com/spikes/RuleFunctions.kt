package com.spikes


class Rule<out T: Any>(private val condition: (T) -> Boolean, argsForCondition: T) {

    val args = argsForCondition

    operator fun invoke(): Boolean{
        condition.invoke(args)
        return true
    }
}


fun boardIsFull(board: Board): Boolean =
        board.cells.all { row -> row.all {it == Cell.A}} || board.cells.all {row -> row.all {it == Cell.D}}

fun checkWholeBoard(board: Board, num: Int = 3, booleanCondition: Boolean): Boolean {

    while(!boardIsFull(board)) {
        val max = board.cells.size - 1
        val y = board.cells[0].size - 1

        (0..max).forEach { i ->
            (0..y).forEach {
                println("testing $i, $it...")


                //TODO try to abstract this
                hasNumberOfLivingNeighbors(num, i, it, board, max)
                board.cells.asSequence().forEach { println(it) }
                println("---------------------------------------------")
            }
        }
    }
    return booleanCondition

}

//TODO decouple the function dependencies
//TODO find better return values
//TODO name better
fun hasNumberOfLivingNeighbors(num: Int, x: Int, y: Int, board: Board, max: Int = 0, min: Int = 0): Boolean {


    val xCoords: List<Int> = boundryCheckBuilder(x, max)
    val yCoords: List<Int> = boundryCheckBuilder(y, max)
    var count = 0
    for(i in xCoords) {
        for(j in yCoords) {
            if(board.cells[x + i][y + j] == Cell.A) count++
            println(count)

        }
    }

    when(board.cells[x][y]){
        Cell.A -> {
            if(count >= num - 1) {
                println("keeping $x, $y as A")
            } else {
                board.cells[x][y] = Cell.D
                println("changing $x, $y from ${board.cells[x][y]} to D")
            }
        }

        Cell.D -> {
            if(count >= num) {
                board.cells[x][y] = Cell.A
                println("changing $x, $y from ${board.cells[x][y]} to A")
            } else {
                println("keeping $x, $y as D")
            }
        }

    }/*
    if (count >= num - 1 && board.cells[x][y] == Cell.A) {
        board.cells[x][y] = Cell.A
        println("keeping $x, $y as A")
    } else if (count >= num ){
        println("changing $x, $y from ${board.cells[x][y]} to A")
        board.cells[x][y] = Cell.A
    } else if(count < num && board.cells[x][y] == Cell.A){
        println("changing $x, $y from ${board.cells[x][y]} to D")
        board.cells[x][y] = Cell.D
    } else {
        board.cells[x][y] = Cell.D
    }
*/
    return true

}

fun boundryCheckBuilder(i: Int, max: Int, min: Int = 0): List<Int> {

    if(i > max || i < min) throw IllegalAccessError()

    return when(i) {

        min -> arrayListOf(0, 1)
        max -> arrayListOf(-1, 0)
        else -> arrayListOf(-1, 0, 1)

    }

}