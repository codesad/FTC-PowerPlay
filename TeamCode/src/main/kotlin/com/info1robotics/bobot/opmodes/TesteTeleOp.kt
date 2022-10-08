package com.info1robotics.bobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.info1robotics.bobot.Common.GamepadEx.Digital.DPAD_UP
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.info1robotics.bobot.tasks.TaskBuilder.loop
import com.info1robotics.bobot.tasks.TaskBuilder.wait

@TeleOp(name = "dpad slider")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = true

    override val loopTask = all {
            + digital(DPAD_UP) {
                on(PRESS) {
                    + { println("on press") }
                    + wait(1000)
                    + { println("after 1 second") }
                }
                on(RELEASE) {
                    + { println("on release") }
                }
                on(HOLD) {
                    + { println("on hold") }
                }
            }
        }
}