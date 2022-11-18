package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode

/**
 * Listener for analog inputs.
 * Never ends, intended to be used with [AllTasks].
 * See [TaskBuilder.analog].
 * @param button The analog input to listen for.
 */
class AnalogTask(private val button: GamepadEx.Analog, private val gamepad: Int) : Task() {
    enum class Type {
        HOLD,
        RELEASE
    }
    var tasks = mutableMapOf<Type, SyncTasks>()
    var position = 0f

    override fun tick() {
        if (context !is TeleOpMode) {
            throw Exception("AnalogTask can only be used in TeleOp")
        }
        val teleOpMode = context as TeleOpMode
        val gamepadEx = if (gamepad == 1) teleOpMode.gamepadEx else teleOpMode.gamepadEx2
        tasks.forEach { (type, action) ->
            if (action.isRunning()) action.tick()
            when (type) {
                Type.HOLD -> {
                    if (gamepadEx.getAnalog(button) > 0.08) {
                        action.start(context)
                    }
                }
                Type.RELEASE -> {
                    if (position != gamepadEx.getAnalog(button) && gamepadEx.getAnalog(button) == 0f) {
                        action.start(context)
                    }
                }
            }
            position = gamepadEx.getAnalog(button)
        }
    }

    /**
     * Registers tasks to be synchronously executed when the button is executed with the given type.
     * @param type The action type. See [Type].
     * @param init Lambda to initialise the action. See [SyncTasks].
     */
    fun on(type: Type, init: SyncTasks.() -> Unit): SyncTasks {
        val task = SyncTasks()
        task.init()
        tasks[type] = task
        return task
    }
}