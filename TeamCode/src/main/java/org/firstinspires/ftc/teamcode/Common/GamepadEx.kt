package org.firstinspires.ftc.teamcode.Common

import com.qualcomm.robotcore.hardware.Gamepad
import java.lang.reflect.Field

class GamepadEx(private val gamepad: Gamepad) {
    enum class Digital(val int: Int, val field: Field) {
        START(0, Gamepad::class.java.getField("start")),
        BACK(1, Gamepad::class.java.getField("back")),
        MODE(2, Gamepad::class.java.getField("guide")),
        A(3, Gamepad::class.java.getField("a")),
        B(4, Gamepad::class.java.getField("b")),
        X(5, Gamepad::class.java.getField("x")),
        Y(6, Gamepad::class.java.getField("y")),
        DPAD_UP(7, Gamepad::class.java.getField("dpad_up")),
        DPAD_RIGHT(8, Gamepad::class.java.getField("dpad_right")),
        DPAD_DOWN(9, Gamepad::class.java.getField("dpad_down")),
        DPAD_LEFT(10, Gamepad::class.java.getField("dpad_left")),
        BUMPER_LEFT(11, Gamepad::class.java.getField("left_bumper")),
        BUMPER_RIGHT(12, Gamepad::class.java.getField("right_bumper")),
        JOYSTICK_LEFT(13, Gamepad::class.java.getField("left_stick_button")),
        JOYSTICK_RIGHT(15, Gamepad::class.java.getField("right_stick_button")),
    }

    enum class Analog(val int: Int, val field: Field) {
        JOYSTICK_LEFT_X(2, Gamepad::class.java.getField("left_stick_x")),
        JOYSTICK_LEFT_Y(3, Gamepad::class.java.getField("left_stick_y")),
        JOYSTICK_RIGHT_X(4, Gamepad::class.java.getField("right_stick_x")),
        JOYSTICK_RIGHT_Y(5, Gamepad::class.java.getField("right_stick_y")),
        TRIGGER_LEFT(6, Gamepad::class.java.getField("left_trigger")),
        TRIGGER_RIGHT(7, Gamepad::class.java.getField("right_trigger"));
    }

    private var stateMap = HashMap<Digital, Boolean>()
    private val downMap = HashMap<Digital, Boolean>()
    private val upMap = HashMap<Digital, Boolean>()
    private val analogMap = HashMap<Analog, Float>()

    init {
        update()
    }

    fun update() {
        for (key in Analog.values()) {
            analogMap[key] = key.field.get(gamepad) as Float
        }
        val newStateMap = Digital.values().map { key ->
            key to key.field.getBoolean(gamepad)
        }.toMap() as HashMap

        for (key in Digital.values()) {
            downMap[key] = newStateMap[key]!! && newStateMap[key] != stateMap[key]
            upMap[key] = !newStateMap[key]!! && newStateMap[key] != stateMap[key]
        }

        stateMap = newStateMap
    }

    fun getAnalog(key: Analog): Float {
        return analogMap[key]!!
    }

    fun getButton(key: Digital): Boolean {
        return stateMap[key]!!
    }

    fun getButtonDown(key: Digital): Boolean {
        return downMap[key]!!
    }

    fun getButtonUp(key: Digital): Boolean {
        return upMap[key]!!
    }
}