package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.info1robotics.bobot.OpModes.templates.TeleOpMode;
import com.info1robotics.bobot.tasks.AllTask;
import com.info1robotics.bobot.tasks.AnalogTask;
import com.info1robotics.bobot.tasks.ClawTask;
import com.info1robotics.bobot.tasks.DigitalTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.info1robotics.bobot.tasks.LinkageTask;
import com.info1robotics.bobot.tasks.PivotTask;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Default TeleOp")
public class OfficialTeleOp extends TeleOpMode {
    @Override
    public void onInit() {
        super.onInit();
        useOmniMecanum = true;
        task = new AllTask(
                new DigitalTask("a", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new ClawTask()
                    )
                ),
                new DigitalTask("b", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new InlineTask(() -> {
                            System.out.println("Testestest.");
                        })
                    )
                ),
                new DigitalTask("bumper_left", 2,
                        new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                                new InlineTask(() -> {
                                    linkageLeft.setPower(0.8);
                                    linkageRight.setPower(-0.8);
                                })
                        ),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE,
                                new InlineTask(() -> {
                                    linkageLeft.setPower(0.0);
                                    linkageRight.setPower(0.0);
                                })
                        )
                ),
                new DigitalTask("bumper_right", 2,
                        new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                                new InlineTask(() -> {
                                    linkageLeft.setPower(-0.8);
                                    linkageRight.setPower(0.8);
                                })
                        ),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE,
                                new InlineTask(() -> {
                                    linkageLeft.setPower(0.0);
                                    linkageRight.setPower(0.0);
                                })
                        )
                ),
                new DigitalTask("dpad_up", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new PivotTask(PivotTask.Level.HIGH)
                    )
                ),
                new DigitalTask("dpad_right", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new PivotTask(PivotTask.Level.MID)
                    )
                ),
                new DigitalTask("dpad_left", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new PivotTask(PivotTask.Level.LOW)
                    )
                ),
                new DigitalTask("dpad_down", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new PivotTask(PivotTask.Level.GROUND)
                    )
                )
        );
    }
}
