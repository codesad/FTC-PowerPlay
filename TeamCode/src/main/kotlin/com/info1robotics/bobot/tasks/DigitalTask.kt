package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode

/**
 * Listener for digital buttons.
 * Never ends, intended to be used with [AllTasks].
 * See [TaskBuilder.digital].
 * @param button The digital button to listen for.
 */
class DigitalTask(private val button: GamepadEx.Digital, private val gamepad: Int) : Task() {
    enum class Type {
        PRESS,
        RELEASE,
        HOLD
    }
    var tasks = mutableMapOf<Type, SyncTasks>()

    override fun tick() {
//        println("ticking")
        if (context !is TeleOpMode) {
            throw Exception("DigitalTask can only be used in TeleOp")
        }
        val teleOpMode = (context) as TeleOpMode
        tasks.forEach { (type, action) ->
            if (action.isRunning()) action.tick()
            val gamepadEx = if (gamepad == 1) teleOpMode.gamepadEx else teleOpMode.gamepadEx2
//            println(gamepadEx)
            when (type) {
                Type.PRESS -> {
                    if (gamepadEx.getButtonDown(button)) {
                        action.start(context)
                    }
                }
                Type.RELEASE -> {
                    if (gamepadEx.getButtonUp(button)) {
                        action.start(context)
                    }
                }
                Type.HOLD -> {
                    if (gamepadEx.getButton(button)) {
                        println("Holding $button")
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