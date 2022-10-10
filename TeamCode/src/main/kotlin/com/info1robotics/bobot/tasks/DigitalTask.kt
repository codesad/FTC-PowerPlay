package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.opmodes.TeleOpMode

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

    fun on(type: Type, block: SyncTasks.() -> Unit): SyncTasks {
        val task = SyncTasks()
        task.block()
        tasks[type] = task
        return task
    }
}