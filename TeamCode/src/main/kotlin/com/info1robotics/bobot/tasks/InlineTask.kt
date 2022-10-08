package com.info1robotics.bobot.tasks

class InlineTask(private val task: () -> Any) : Task() {
    override fun run() {
        task()
        state = State.FINISHED
    }
}