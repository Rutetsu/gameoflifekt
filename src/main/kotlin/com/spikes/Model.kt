package com.spikes

enum class Cell {
    A,
    D
}

data class Board(private val boardSize: Int) {

    var cells: MutableList<MutableList<Cell>> =
            MutableList(boardSize, {
                MutableList(boardSize, { Cell.D })
            })


}




