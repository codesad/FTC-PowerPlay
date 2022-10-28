package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.tasks.ClawTask
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.TaskBuilder
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class SliderClawTest :TeleOpMode(){
    override var useOmniMecanum = true

    override val task = TaskBuilder.all {
        +{
           telemetry.addLine(if (clawOpen) "open" else "closed")
            telemetry.update()
        }



        +digital(A)
        {
            on(PRESS)
            {
                +ClawTask()
            }
        }
        +digital(DPAD_DOWN)
        {
            on(PRESS)
            {
            +{+SliderTask(SliderTask.Level.LOW)
                if(clawOpen)
                {    claw.position=.8;
                clawOpen=false;}
            }
            }

        }
        +digital(X)
        {
            on(PRESS)
            {
                +SliderTask(SliderTask.Level.GROUND)
                +{
                    if(clawOpen)
                    {    claw.position=.8;
                        clawOpen=false;}
                }
                +{
                    if(!clawOpen)
                    {    claw.position=.6;
                        clawOpen=true;}
                }
            }

        }
        +digital(DPAD_UP)
        {
            on(PRESS)
            {
                +{+SliderTask(SliderTask.Level.HIGH)
                    if(clawOpen)
                    {    claw.position=.8;
                        clawOpen=false;}
                }
            }

        }
        +digital(DPAD_LEFT)
        {
            on(PRESS)
            {
                +{+SliderTask(SliderTask.Level.MEDIUM)
                    if(clawOpen)
                    {
                        claw.position=.8;
                        clawOpen=false;
                    }
                }
            }
        }
    }

    }