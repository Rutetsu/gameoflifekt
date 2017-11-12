package com.spikes

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class RuleFunctionsKtTest {


    lateinit var board: Board


    @BeforeEach
    fun setUp(){
        board = Board(5)
    }
    @Test
    fun checkBoundry() {


    }

    @Test
    fun hasNumberOfLivingNeighbors() {
    }

    @Test
    fun testTheCheckWholeBoard() {

        checkWholeBoard(board, 3, boardIsFull(board))

    }
}