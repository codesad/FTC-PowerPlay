package com.info1robotics.bobot.tasks
import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.*

/**
 * Task builder for Kotlin DSL.
 * See [TeleOpMode], [AutoOpMode]
 */
object TaskBuilder {
    fun repeat(block: LoopTasks.() -> Unit): LoopTasks {
        val task = LoopTasks()
        task.block()
        return task
    }

    fun sync(block: SyncTasks.() -> Unit): SyncTasks {
        val task = SyncTasks()
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

    fun wait(condition: () -> Boolean): SleepWhile {
        return SleepWhile(condition)
    }

    fun analog(button: GamepadEx.Analog, block: AnalogTask.() -> Unit): AnalogTask {
        val task = AnalogTask(button)
        task.block()
        return task
    }
}