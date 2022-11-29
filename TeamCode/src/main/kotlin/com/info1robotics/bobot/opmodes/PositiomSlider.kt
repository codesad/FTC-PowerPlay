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
class PositiomSlider:TeleOpMode() {
    override val task: AllTasks = all {
        +digital(DPAD_UP) {
            on(DigitalTask.Type.PRESS) {
                +SliderTask(SliderTask.Level.MID)
            }
        }
        +digital(DPAD_DOWN) {
            on(DigitalTask.Type.PRESS) {
                + {
                    sliderRight.power = -1.0
                    sliderLeft.power = -1.0
                }
            }
            on(DigitalTask.Type.RELEASE) {
                + {
                    sliderRight.power = 0.0
                    sliderLeft.power = 0.0
                }
            }
        }
    }
}