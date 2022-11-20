package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.Common.GamepadEx.Analog.*
import com.info1robotics.bobot.tasks.ClawTask
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.LinkageTask
import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.TaskBuilder
import com.info1robotics.bobot.tasks.TaskBuilder.analog
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Official TeleOP")
class SliderClawTest : TeleOpMode() {
    override var useOmniMecanum = true
    override val task = TaskBuilder.all {
        +{
            telemetry.addLine(if (clawOpen) "open" else "closed")
            telemetry.update()
        }
        +digital(A, 2)
        {
            on(PRESS)
            {
                +ClawTask()
            }
        }
        +digital(DPAD_UP,2)
        {
            on(PRESS)
            {
                +SliderTask(.7)
            }
            on(RELEASE)
            {
                +SliderTask(0.0)
            }
        }
        +digital(DPAD_DOWN,2)
        {
            on(PRESS)
            {
                +SliderTask(-.7)
            }
            on(RELEASE)
            {
                +SliderTask(0.0)
            }
        }

        +digital(BUMPER_RIGHT,2)
        {
            on(HOLD)
            {
                +LinkageTask(LinkageTask.Direction.EXTEND)
            }
            on(RELEASE) {
                +LinkageTask(LinkageTask.Direction.NONE)
            }
        }
        +digital(BUMPER_LEFT,2)
        {
            on(HOLD)
            {
                +LinkageTask(LinkageTask.Direction.LOWER)
            }
            on(RELEASE) {
                +LinkageTask(LinkageTask.Direction.NONE)
            }
        }
    }
}
