package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait

class TesteAutoOp : AutoOpMode() {
    override val task: Task = all {
        + { println("Begin Auto") }
        + sync {
            + SliderTask(SliderTask.Level.GROUND)
        }
        + wait { false }
    }
}