package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx

object TaskBuilder {
    fun loop(block: LoopTasks.() -> Unit): LoopTasks {
        val task = LoopTasks()
        task.block()
        return task
    }

    fun all(block: AllTasks.() -> Unit): AllTasks {
        val task = AllTasks()
        task.block()
        return task
    }

    fun digital(button: GamepadEx.Digital, block: DigitalTask.() -> Unit): DigitalTask {
        val task = DigitalTask(button)
        task.block()
        return task
    }

    fun wait(time: Int): SleepTask {
        return SleepTask(time)
    }
}