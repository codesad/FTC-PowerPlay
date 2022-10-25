package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode
import com.info1robotics.bobot.tasks.DigitalTask.Type

/**
 * Listener for analog inputs.
 * Never ends, intended to be used with [AllTasks].
 * See [TaskBuilder.analog].
 * @param button The analog input to listen for.
 */
class AnalogTask(private val button: GamepadEx.Analog) : Task() {
    enum class Type {
        HOLD
    }
    var tasks = mutableMapOf<Type, SyncTasks>()

    override fun tick() {
        if (context !is TeleOpMode) {
            throw Exception("AnalogTask can only be used in TeleOp")
        }
        tasks.forEach { (type, action) ->
            if (action.isRunning()) action.tick()
            val teleOpMode = context as TeleOpMode
            when (type) {
                Type.HOLD -> {
                    if (teleOpMode.gamepadEx.getAnalog(button) > 0.08) {
                        action.start(context)
                    }
                }
            }
        }
    }

    /**
     * Registers tasks to be synchronously executed when the button is executed with the given type.
     * @param type The action type. See [Type].
     * @param init Lambda to initialise the action. See [SyncTasks].
     */
    fun on(type: Type, init: SyncTasks.() -> Unit) {
        val task = SyncTasks()
        task.init()
        tasks[type] = task
    }
}