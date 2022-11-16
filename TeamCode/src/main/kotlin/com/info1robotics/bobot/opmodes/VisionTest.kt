package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name="Vision Test")
class VisionTest : AutoOpMode() {
    override val task: Task = sync {
        +wait(4000)
        + {
            telemetry.addData("Detected: ", atDetection.detectZone())
            telemetry.update()
        }
    }
}