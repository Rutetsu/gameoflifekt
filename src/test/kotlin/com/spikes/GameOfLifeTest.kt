package com.spikes

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

internal class GameOfLifeTest {

    lateinit var ofLife: GameOfLife

    @BeforeEach
    fun setUp() {
        ofLife = GameOfLife(Board(5))
    }

    @Test
    fun addRule() {
        ofLife.initialize()
        assertTrue(ofLife.ruleList.isEmpty())
        ofLife.addRule(Rule({b -> checkWholeBoard(b, 3, boardIsFull(ofLife.board)) }, ofLife.board ))
        assertTrue(ofLife.ruleList[0].invoke())
    }

    @Test
    fun isStarted() {
        ofLife.initialize()
        assertTrue(ofLife.isStarted)
    }


    @Test @DisplayName("Initialize will randomly flip cells to activated or de-activated")
    fun initialize() {
        ofLife.initialize()
        assertTrue(ofLife.board.cells
                .asSequence()
                .any{row ->
                        row.any {cell -> cell == Cell.A}})
    }

    @Test
    @RepeatedTest(5)
    @DisplayName("Progress should continue until all cells are activated")
    fun progress() {


        ofLife.initialize()
        ofLife.addRule(Rule({ b -> checkWholeBoard(b, 3, boardIsFull(ofLife.board)) }, ofLife.board))
        ofLife.progress()

        assertTrue(ofLife.board.cells.asSequence().all { row -> row.all { it == Cell.A || it == Cell.D } })

    }


}