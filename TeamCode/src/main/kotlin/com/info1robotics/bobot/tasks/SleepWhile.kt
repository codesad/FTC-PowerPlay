package com.info1robotics.bobot.tasks

/**
 * Waits while the given condition is true.
 * Unless used in a synchronous task, this will not block the thread of execution.
 * @param condition Lambda that returns true if the task should continue waiting.
 */
class SleepWhile(private val condition: () -> Boolean) : Task() {
    override fun tick() {
        if (!condition()) {
            state = State.FINISHED
        }
    }
}