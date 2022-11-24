package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.tasks.AllTasks
import com.info1robotics.bobot.tasks.DigitalTask
import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.async
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class PositiomSlider:AutoOpMode() {
    override val task: AllTasks = all {
        +SliderTask(SliderTask.Level.LOW)


    }
}