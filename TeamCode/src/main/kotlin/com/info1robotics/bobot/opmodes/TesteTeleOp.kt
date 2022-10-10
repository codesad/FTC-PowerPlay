package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.DPAD_UP
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "dpad slider")
class TesteTeleOp : TeleOpMode() {
    override val task = all {
        +digital(DPAD_UP) {
            on(PRESS) {
                +{ println("on press") }
                +wait(1000)
                +{ println("after 1 second") }
            }
            on(RELEASE) {
                +{ println("on release") }
            }
            on(HOLD) {
                +{ println("on hold") }
            }
        }
    }
}
