package com.info1robotics.bobot.tasks

class ParallelTasks : Task() {
    var finishedTasks = 0

    override fun tick() {
        finishedTasks = 0
        children.forEach {
            if (it.isFinished()) {
                it.tick()
            } else if (it.isFinished()) {
                finishedTasks++
            }
        }
        if (finishedTasks == children.size) {
            state = State.FINISHED
        }
    }

    override fun run() {
        for (task in children) {
            task.start(this.context)
        }
    }

    companion object {
        fun parallel(init: ParallelTasks.() -> Unit): ParallelTasks {
            val tasks = ParallelTasks()
            tasks.init()
            return tasks
        }
    }
}