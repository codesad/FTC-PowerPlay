package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name = "park test")
class ParkOpModeTest : AutoOpMode() {
    override val task: Task = sync {
//        telemetry.addData("parkzone", atDetection.detectZone())
//        telemetry.update()
    }
}