package com.info1robotics.bobot.opmodes
import com.info1robotics.bobot.tasks.MoveTask
import com.info1robotics.bobot.tasks.SleepTask
import com.info1robotics.bobot.tasks.SyncTasks
import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous(name="Default Auto")
class AutoLeft :AutoOpMode() {
    override val task: Task=sync{
        +MoveTask(0.0, 1.0, 0.0, .5, 640)
        +SleepTask(300)
        +{
            val park = atDetection.detectZone()
            if (park == 1) {
                +sync {
                    +MoveTask(-1.0, 0.0, 0.0, .5, 650)
                    +SleepTask(300)
                    +MoveTask(0.0, 1.0, 0.0, .5, 30)
                }
            } else if (park == 3) {
                +MoveTask(1.0, 0.0, 0.05, .5, 700)
                +SleepTask(300)


            }
        }
    }
}