package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.opmodes.ImplOpMode

/**
 * Main element of the state machine.
 * It allows you to asynchronously execute code on the same thread.
 * It is recommended to use Kotlin DSL for creating tasks. See [TaskBuilder].
 */
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

    /**
     * Appends the added task as a child to its parent.
     */
    open operator fun Task.unaryPlus() {
        this@Task.children.add(this) // not sure if this is the best way to do this
    }

    /**
     * Appends the lambda as an inline task to its parent.
     */
    operator fun (() -> Any).unaryPlus() {
        this@Task.children.add(InlineTask(this))
    }
}