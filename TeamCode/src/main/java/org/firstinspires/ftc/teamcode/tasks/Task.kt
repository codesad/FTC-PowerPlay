package org.firstinspires.ftc.teamcode.tasks

import org.firstinspires.ftc.teamcode.OpModes.ImplOpMode

enum class TaskState {
    DEFAULT,
    RUNNING,
    FINISHED,
}

abstract class Task {
    var state = TaskState.DEFAULT
    open fun tick() {}
    abstract fun run()
    lateinit var context: ImplOpMode
    var children = mutableListOf<Task>()
    fun start(ctx: ImplOpMode) {
        state = TaskState.RUNNING
        context = ctx
        run()
    }
    fun isRunning() = state == TaskState.RUNNING
    fun isFinished() = state == TaskState.FINISHED

    operator fun Task.unaryPlus() {
        this@Task.children.add(this) // adds the task to the children of the parent task. not sure if this is the best way to do this
    }
}