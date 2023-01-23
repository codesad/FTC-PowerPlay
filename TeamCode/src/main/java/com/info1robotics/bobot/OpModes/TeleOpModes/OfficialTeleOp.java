package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.info1robotics.bobot.OpModes.templates.TeleOpMode;
import com.info1robotics.bobot.tasks.AllTask;
import com.info1robotics.bobot.tasks.ClawTask;
import com.info1robotics.bobot.tasks.DigitalTask;
import com.info1robotics.bobot.tasks.InlineTask;
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
                new DigitalTask("dpad_down", 2,
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
                new DigitalTask("dpad_up", 2,
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
                                    pivotLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                                    pivotRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                                    pivotLeft.setPower(0.8);
                                    pivotRight.setPower(0.8);
                                })
                        ),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE,
                                new InlineTask(() -> {
                                    pivotLeft.setTargetPosition(pivotLeft.getCurrentPosition());
                                    pivotRight.setTargetPosition(pivotRight.getCurrentPosition());
                                    pivotLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                    pivotRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                    pivotLeft.setPower(0.8);
                                    pivotRight.setPower(0.8);
                                })
                        )
                ),
                new DigitalTask("bumper_left", 2,
                        new DigitalTask.ActionPair(DigitalTask.Type.PRESS,
                                new InlineTask(() -> {
                                    pivotLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                                    pivotRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                                    pivotLeft.setPower(-0.8);
                                    pivotRight.setPower(-0.8);
                                })
                        ),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE,
                                new InlineTask(() -> {
                                    pivotLeft.setTargetPosition(pivotLeft.getCurrentPosition());
                                    pivotRight.setTargetPosition(pivotRight.getCurrentPosition());
                                    pivotLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                    pivotRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                    pivotLeft.setPower(0.8);
                                    pivotRight.setPower(0.8);
                                })
                        )
                )
        );
    }
}
