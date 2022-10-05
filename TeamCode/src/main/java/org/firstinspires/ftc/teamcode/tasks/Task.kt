package org.firstinspires.ftc.teamcode.tasks

import org.firstinspires.ftc.teamcode.OpModes.StateOpMode

enum class TaskState {
    RUNNING,
    FINISHED,
}

abstract class Task {
    var state = TaskState.RUNNING
    open fun tick() {}
    abstract fun run()
    lateinit var context: StateOpMode
    fun start(ctx: StateOpMode) {
        state = TaskState.RUNNING
        context = ctx
        run()
    }
    fun isRunning() = state == TaskState.RUNNING
    fun isFinished() = state == TaskState.FINISHED
}