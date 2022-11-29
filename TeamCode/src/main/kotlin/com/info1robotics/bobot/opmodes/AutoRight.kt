package com.info1robotics.bobot.opmodes
import com.info1robotics.bobot.tasks.*
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor

@Autonomous(name="Default Auto Right")
class AutoRight : AutoOpMode() {
    override val task: Task = sync {
//        +SliderTask(.7,SliderTask.Level.MANUAL_UP)
//        +wait(700)
//        +SliderTask(.0,SliderTask.Level.CURRENT_POSITION)
//        +wait(2000)
        +SliderTask(SliderTask.Level.MID)
        +wait(1000)
        +MoveTask(1.0, 0.0, 0.0, .5, 800)
        +wait(2000)
        +MoveTask(-1.0, 0.0, 0.0, .5, 400)
        +wait(2000)
        +MoveTask(-1.0, 0.0,0.0, .5, 65)
        +wait(300)
        +MoveTask(0.0, 1.0, 0.0, .5, 640)
        +wait(300)
        +{
            if (zone == 1) {
                +sync {
                    +MoveTask(-1.0, 0.0, 0.0, .5, 650)
                    +SleepTask(300)
                    +MoveTask(0.0, 1.0, 0.0, .5, 30)
                }
            } else if (zone == 3) {
                +MoveTask(1.0, 0.0, 0.00, .5, 790)
                +SleepTask(300)
                +MoveTask(0.0, 1.0, 0.00, .5, 150)
            }
        }
    }
}