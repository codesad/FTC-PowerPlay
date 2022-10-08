package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode

class AnalogTask(val button: GamepadEx.Analog) : Task() {
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

    fun on(type: Type, block: SyncTasks.() -> Unit) {
        val task = SyncTasks()
        task.block()
        tasks[type] = task
    }
}