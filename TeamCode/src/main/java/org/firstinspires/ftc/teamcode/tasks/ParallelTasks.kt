package org.firstinspires.ftc.teamcode.tasks

class ParallelTasks : Task() {
    var finishedTasks = 0

    override fun tick() {
        finishedTasks = 0
        children.forEach {
            if (it.state == TaskState.RUNNING) {
                it.tick()
            } else if (it.state == TaskState.FINISHED) {
                finishedTasks++
            }
        }
        if (finishedTasks == children.size) {
            state = TaskState.FINISHED
        }
    }

    override fun run() {
        for (task in children) {
            task.start(this.context)
        }
    }
    
    operator fun (() -> Unit).unaryPlus() {
        children.add(InlineTask(this))
    }

    companion object {
        fun parallel(init: ParallelTasks.() -> Unit): ParallelTasks {
            val tasks = ParallelTasks()
            tasks.init()
            return tasks
        }
    }
}