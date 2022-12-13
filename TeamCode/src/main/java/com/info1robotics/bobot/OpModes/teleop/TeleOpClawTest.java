package com.info1robotics.bobot.OpModes.teleop;

import com.info1robotics.bobot.Common.GamepadEx;
import com.info1robotics.bobot.OpModes.templates.TeleOpMode;
import com.info1robotics.bobot.tasks.AllTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Claw Test", group = "TeleOp")
public class TeleOpClawTest extends TeleOpMode {
    @Override
    public void onInit() {
        super.onInit();
        task = new AllTask(
                new InlineTask(() -> {
                    if (gamepadEx.getButtonDown("a")) {
                        linkageLeft.setPosition(1.0);
                        linkageRight.setPosition(0.0);
                    }
                }),

                new InlineTask(() -> {
                    if (gamepadEx.getButtonDown("b")) {
                        linkageLeft.setPosition(0.0);
                        linkageRight.setPosition(1.0);
                    }
                })
        );
    }
}
