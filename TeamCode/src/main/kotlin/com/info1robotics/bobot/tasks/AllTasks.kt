package com.info1robotics.bobot.tasks

/**
 * Asynchronously starts and ticks each child.
 * Ends when all children finish.
 */
open class AllTasks() : Task() {

    override fun tick() {
        if(children.all { it.isFinished() }) {
            state = State.FINISHED
        } else {
            children.forEach {
                if(it.isRunning()) {
                    it.tick()
                }
            }
        }
    }

    override fun run() {
        children.forEach { it.start(context) }
    }
}
