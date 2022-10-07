package org.firstinspires.ftc.teamcode.tasks

import android.util.Log
import org.firstinspires.ftc.teamcode.Common.GamepadEx

enum class DigitalType {
    ONCE,
    HOLD,
    RELEASE,
}

enum class AnalogType {
    HOLD
}


data class DigitalAction(val button: GamepadEx.Digital, val type: DigitalType) : Action()
data class AnalogAction(val button: GamepadEx.Analog, val type: AnalogType) : Action()

abstract class Action() {
    var tasks = mutableListOf<Task>()
    var currentTask = 0
    operator fun Task.unaryPlus() {
        tasks.add(this)
    }

    operator fun (() -> Any).unaryPlus() {
        tasks.add(InlineTask(this))
    }
}

class TeleOpBuilder() {
    var useOmniMecanum: Boolean = false
    private val digitalActions = mutableListOf<DigitalAction>()
    private val analogActions = mutableListOf<AnalogAction>()

    fun buttonAction(button: GamepadEx.Digital, type: DigitalType, init: DigitalAction.() -> Unit): DigitalAction {
        val action = DigitalAction(button, type)
        action.init()
        digitalActions.add(action)
        return action
    }

    fun buttonAction(button: GamepadEx.Analog, type: AnalogType, init: AnalogAction.() -> Unit): AnalogAction {
        val action = AnalogAction(button, type)
        action.init()
        analogActions.add(action)
        return action
    }

    fun build(gamepadEx: GamepadEx): TeleOpTasks {
        return TeleOpTasks(digitalActions, analogActions, gamepadEx)
    }


    companion object {
        @JvmStatic
        fun teleop(init: TeleOpBuilder.() -> Unit): TeleOpBuilder {
            val builder = TeleOpBuilder()
            builder.init()
            return builder
        }

        @JvmStatic
        fun wait(time: Int): SleepTask {
            return SleepTask(time)
        }
    }
}