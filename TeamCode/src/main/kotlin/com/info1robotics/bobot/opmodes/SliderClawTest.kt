package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.Common.GamepadEx.Analog.*
import com.info1robotics.bobot.tasks.*
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
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

        +analog(TRIGGER_RIGHT)
        {
            on(AnalogTask.Type.HOLD)
            {
               +{
                   sliderLeft.power=position
                   sliderRight.power=position
               }
            }
            on(AnalogTask.Type.RELEASE)
            {
                +{
                    sliderLeft.power=.0
                    sliderRight.power=.0
                }
            }
        }
        +analog(TRIGGER_LEFT)
        {
            on(AnalogTask.Type.HOLD)
            {
                +{
                    sliderLeft.power=-1*position
                    sliderRight.power=-1*position
                }
            }
            on(AnalogTask.Type.RELEASE)
            {
                +{
                    sliderLeft.power=.0
                    sliderRight.power=.0
                }
            }
        }
        +analog(JOYSTICK_LEFT_Y)
        {
            on(AnalogTask.Type.HOLD)
            {
                +LinkageTask(position,LinkageTask.Direction.EXTEND)

            }
            on(AnalogTask.Type.RELEASE)
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
