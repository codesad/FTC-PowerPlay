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
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Default TeleOp")
public class OfficialTeleOp extends TeleOpMode {
    int currentPosition = 0;
    @Override
    public void onInit() {
        super.onInit();
        useOmniMecanum = true;

        task = new AllTask(
                new DigitalTask("a", 2, new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                        new ClawTask()
                    )
                ),
                new DigitalTask("bumper_left", 2,
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
                new DigitalTask("bumper_right", 2,
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
                new DigitalTask("dpad_up", 2, new DigitalTask.ActionPair(DigitalTask.Type.HOLD,
                        new InlineTask(() -> {
                            currentPosition += 5;
                            if (currentPosition > 1500) currentPosition = 1500;
                            else if (currentPosition <= 0) currentPosition = 0;
                        })
                    )
                ),
                new DigitalTask("dpad_down", 2, new DigitalTask.ActionPair(DigitalTask.Type.HOLD,
                        new InlineTask(() -> {
                            currentPosition -= 5;
                            if (currentPosition > 1500) currentPosition = 1500;
                            else if (currentPosition <= 0) currentPosition = 0;
                        })
                    )
                )
        );
    }

    @Override
    public void onLoop() {
        super.onLoop();
        pivotLeft.setTargetPosition(currentPosition);
        pivotRight.setTargetPosition(currentPosition);
        pivotLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivotRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pivotLeft.setPower(0.8);
        pivotRight.setPower(0.8);
    }
}
