package com.spikes

fun main(args: Array<String>) {

    val board = Board(10)
    val gameOfLife = GameOfLife(board)
    val game = Game(gameOfLife)
    game.currentSim
            .addRule(Rule({b -> checkWholeBoard(b, 3, boardIsFull(game.currentSim.board)) }, game.currentSim.board ))

    game.start()
}