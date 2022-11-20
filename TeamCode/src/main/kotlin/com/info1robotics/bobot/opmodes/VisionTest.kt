package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name="Vision Test")
class VisionTest : AutoOpMode() {
    override val task: Task = sync {
        +wait(10000)
        + {
            println("VISION TEST")
            println("DETECTED TAG: ${aprilTag.zone}")
            telemetry.addData("Detected: ", aprilTag.detectZone())
            telemetry.update()
        }
    }
}