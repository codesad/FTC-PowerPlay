package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class TestAuto3 : AutoOpMode() {
    override fun onLoop() {
    }

    override val task: Task = sync {
        + SliderTask(SliderTask.Level.MID)
        + wait(10000)
    }
}