package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Movement")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = false

    override val task = all {

    }
}
