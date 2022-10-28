package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.MoveTask
import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Movement")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = false

    override val task = all {
       +digital(DPAD_UP)
       {
           on(PRESS)
           {
               +MoveTask(0.0,1.0,0.0,.4,300)
           }
       }
        +digital(DPAD_RIGHT)
        {
            on(PRESS)
            {
                +MoveTask(1.0,0.0,0.0,.5,500)
            }
        }
        +digital(DPAD_LEFT)
        {
            on(PRESS)
            {
                +MoveTask(-1.0,0.0,0.0,.5,500)
            }
        }
        +digital(DPAD_DOWN)
        {
            on(PRESS)
            {
                +MoveTask(0.0,-1.0,0.0,.5,500)
            }
        }
  +digital(BUMPER_RIGHT)
{
  on(PRESS)
  {+MoveTask(0.0,0.0,1.0,.5,500)}
}
        +digital(BUMPER_LEFT)
        {
            on(PRESS)
            {
                +MoveTask(0.0,0.0,-1.0,.5,500)
            }

        }
    }

}
