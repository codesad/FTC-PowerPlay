package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.info1robotics.bobot.tasks.Task

/**
 * Synchronously starts and ticks each child.
 * Ends when all children finish.
 */
open class SyncTasks() : Task() {
    var currentTask = 0

    override fun tick() {
        if (children[currentTask].isFinished()) {
            currentTask++
            if (currentTask >= children.size) {
                state = State.FINISHED
                currentTask = 0
                children.forEach { it.state = State.DEFAULT }
                return
            }
            children[currentTask].start(this.context)
        } else if (children[currentTask].isRunning()) {
            children[currentTask].tick()
        }
    }

    override fun run() {
        if (children.isEmpty()) {
            state = State.FINISHED
            return
        }
        children[0].start(this.context)
    }
}
