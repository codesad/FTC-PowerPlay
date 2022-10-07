package org.firstinspires.ftc.teamcode.tasks

import org.firstinspires.ftc.teamcode.Common.GamepadEx

class TeleOpTasks(
        private val digitalActions: List<DigitalAction>,
        private val analogActions: List<AnalogAction>,
        private val gamepadEx: GamepadEx) : Task() {

    private fun startAction(action: Action) {
        if (action.currentTask > 0) return
        action.tasks[0].start(context)
    }

    override fun tick() {
        digitalActions.forEach { action ->
            when(action.type) {
                DigitalType.HOLD -> {
                    if (gamepadEx.getButton(action.button)) {
                        startAction(action)
                    }
                }
                DigitalType.ONCE -> {
                    if (gamepadEx.getButtonDown(action.button)) {
                        startAction(action)
                    }
                }
                DigitalType.RELEASE -> {
                    if (gamepadEx.getButtonUp(action.button)) {
                        startAction(action)
                    }
                }

            }
        }
        analogActions.forEach { action ->
            when (action.type) {
                AnalogType.HOLD -> {
                    if (gamepadEx.getAnalog(action.button) > 0.1) {
                        startAction(action)
                    }
                }
            }
        }

        (digitalActions + analogActions).forEach { action ->
            action.tasks.forEach { task ->
                if (task.isRunning()) {
                    task.tick()
                }
            }

            if (action.tasks[action.currentTask].state == TaskState.FINISHED) {
                action.currentTask ++
                if (action.currentTask >= action.tasks.size) {
                    action.tasks.forEach { task -> task.state = TaskState.DEFAULT }
                    action.currentTask = 0
                    return
                }
                action.tasks[action.currentTask].start(context)
            }
        }


    }

    override fun run() {

    }
}