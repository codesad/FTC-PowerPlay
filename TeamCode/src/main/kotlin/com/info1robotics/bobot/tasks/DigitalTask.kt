package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode

/**
 * Listener for digital buttons.
 * Never ends, intended to be used with [AllTasks].
 * See [TaskBuilder.digital].
 * @param button The digital button to listen for.
 */
class DigitalTask(private val button: GamepadEx.Digital) : Task() {
    enum class Type {
        PRESS,
        RELEASE,
        HOLD
    }
    var tasks = mutableMapOf<Type, SyncTasks>()

    override fun tick() {
        if (context !is TeleOpMode) {
            throw Exception("DigitalTask can only be used in TeleOp")
        }
        tasks.forEach { (type, action) ->
            if (action.isRunning()) action.tick()
            val teleOpMode = context as TeleOpMode
            when (type) {
                Type.PRESS -> {
                    if (teleOpMode.gamepadEx.getButtonDown(button)) {
                        action.start(context)
                    }
                }
                Type.RELEASE -> {
                    if (teleOpMode.gamepadEx.getButtonUp(button)) {
                        action.start(context)
                    }
                }
                Type.HOLD -> {
                    if (teleOpMode.gamepadEx.getButton(button)) {
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
    fun on(type: Type, init: SyncTasks.() -> Unit): SyncTasks {
        val task = SyncTasks()
        task.init()
        tasks[type] = task
        return task
    }
}