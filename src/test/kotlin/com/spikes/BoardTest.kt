package com.spikes

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class BoardTest {


    lateinit var board: Board
    @BeforeEach
    fun setUp(){
        board = Board(5)
    }

    @Test @DisplayName("Fills values with all Deactivated Cells when first initialized")
    fun fillsWithAllDeactivated(){
        assertTrue(board.cells.asSequence().all { row -> row.all { it == Cell.D } })
    }

}