package com.spikes

class Game<out T: Simulatable>(val currentSim: T): GameController {

    override fun start() {
        if(currentSim.isStarted) {
            currentSim.progress()
        } else {
            currentSim.initialize()
            currentSim.progress()

        }
    }


}

interface GameController {

    fun start()

}
