package com.info1robotics.bobot.tasks

/**
 * Wraps the given block in a task.
 * Intended for one-liners.
 * @param task Lambda to be executed.
 */
class InlineTask(private val task: () -> Any) : Task() {
    override fun run() {
        task()
        state = State.FINISHED
    }
}