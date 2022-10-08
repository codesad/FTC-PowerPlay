package com.info1robotics.bobot.tasks

class LoopTasks : Task() {
    var currentTask = 0

    override fun tick() {
        if (children[currentTask].isFinished()) {
            currentTask++
            if (currentTask >= children.size) {
                currentTask = 0
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