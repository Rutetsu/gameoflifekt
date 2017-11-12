package com.spikes

import java.util.*

open class GameOfLife(var board: Board): Simulatable {

    var ruleList: MutableList<Rule<Any>> = mutableListOf()

    override fun <T : Any> addRule(r: Rule<T>) {
        ruleList.add(r)

    }


    override var isStarted: Boolean = false

    private val r: Random = Random()

    private fun randomizeCells() = if (coinFlip()) Cell.A else Cell.D

    private fun coinFlip(): Boolean = r.nextInt(100) > 50

    override fun initialize() {
        isStarted = true
                board.cells = board.cells
                .asSequence()
                .map {
                    it.asSequence()
                            .map {
                                randomizeCells()
                    }.toMutableList()
                }.toMutableList()
    }

    override fun progress() {

            ruleList.asSequence().forEach { it() }

    }
}

interface Simulatable {

    var isStarted: Boolean
    fun <T : Any>addRule(r: Rule<T>)
    fun initialize()
    fun progress()

}



