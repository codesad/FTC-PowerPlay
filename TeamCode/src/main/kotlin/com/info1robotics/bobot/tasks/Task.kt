package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.opmodes.ImplOpMode

abstract class Task {
    enum class State {
        DEFAULT,
        RUNNING,
        FINISHED,
    }

    var state = State.DEFAULT
    open fun tick() {}
    open fun run() {}
    lateinit var context: ImplOpMode
    var children = mutableListOf<Task>()
    fun start(ctx: ImplOpMode) {
        state = State.RUNNING
        context = ctx
        run()
    }
    fun isRunning() = state == State.RUNNING
    fun isFinished() = state == State.FINISHED

    operator fun Task.unaryPlus() {
        this@Task.children.add(this) // adds the task to the children of the parent task. not sure if this is the best way to do this
    }

    operator fun (() -> Any).unaryPlus() {
        this@Task.children.add(InlineTask(this))
    }
}