package com.info1robotics.bobot.tasks

class SleepTask(private val ms: Int) : Task() {
    private var timeAtRun: Long = -1
    override fun tick() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - timeAtRun > ms) {
            state = State.FINISHED
            return
        }
    }

    override fun run() {
        timeAtRun = System.currentTimeMillis()
    }
}