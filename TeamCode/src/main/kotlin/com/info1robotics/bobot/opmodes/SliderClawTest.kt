package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.Common.GamepadEx.Analog.*
import com.info1robotics.bobot.tasks.*
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.TaskBuilder.analog
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

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

        +digital(BUMPER_RIGHT, 2)
        {
            on(HOLD)
            {
               + {
                   sliderLeft.mode = DcMotor.RunMode.RUN_USING_ENCODER
                   sliderRight.mode = DcMotor.RunMode.RUN_USING_ENCODER
                   sliderLeft.power=.8
                   sliderRight.power=.8
               }
            }
            on(RELEASE)
            {
                + {
                    sliderLeft.power=.0
                    sliderRight.power=.0
                }
            }
        }
        +digital(BUMPER_LEFT, 2)
        {
            on(HOLD)
            {
                +{
                    sliderLeft.power=-.8
                    sliderRight.power=-.8
                }
            }
            on(RELEASE)
            {
                +{
                    sliderLeft.power=.0
                    sliderRight.power=.0
                }
            }
        }
        + {
            sliderLeft.mode = DcMotor.RunMode.RUN_USING_ENCODER
            sliderRight.mode = DcMotor.RunMode.RUN_USING_ENCODER
            sliderRight.power = gamepad2.right_trigger - gamepad2.left_trigger.toDouble()
            sliderLeft.power = gamepad2.right_trigger - gamepad2.left_trigger.toDouble()

        }
        +digital(DPAD_UP, 2)
        {
            on(HOLD)
            {
                +LinkageTask(.9,LinkageTask.Direction.EXTEND)
            }
            on(RELEASE)
            {
                +LinkageTask(.0,LinkageTask.Direction.NONE)
            }
        }
        +digital(DPAD_DOWN, 2)
        {
            on(HOLD)
            {
                +LinkageTask(-.9,LinkageTask.Direction.EXTEND)
            }
            on(RELEASE)
            {
                +LinkageTask(.0,LinkageTask.Direction.NONE)
            }
        }
//        +digital(DPAD_UP,2)
//        {
//            on(PRESS)
//            {
//                +{
//                    sliderLeft.power=.7
//                    sliderRight.power=.7
//                }
//            }
//            on(RELEASE)
//            {
//              +{
//                  sliderLeft.power = .000
//                  sliderRight.power = .000
//              }
//            }
//        }
//        +digital(DPAD_DOWN,2)
//        {
//            on(PRESS)
//            {
//
//            }
//            on(RELEASE)
//            {
////                +SliderTask(0.0,SliderTask.Level.CURRENT_POSITION)
//            }
//        }


    }
}
