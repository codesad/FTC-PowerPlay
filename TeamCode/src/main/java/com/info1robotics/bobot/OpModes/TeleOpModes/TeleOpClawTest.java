package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.info1robotics.bobot.OpModes.templates.TeleOpMode;
import com.info1robotics.bobot.tasks.AllTask;
import com.info1robotics.bobot.tasks.AnalogTask;
import com.info1robotics.bobot.tasks.DigitalTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp Claw Test", group = "TeleOp")
public class TeleOpClawTest extends TeleOpMode {
    @Override
    public void onInit() {
        super.onInit();
        task = new AllTask(
                new DigitalTask(
                        "a", 1,
                        new DigitalTask.ActionPair(DigitalTask.Type.HOLD, new InlineTask(() -> {
                            System.out.println("pressed a");
                        })),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE, new InlineTask(() -> {
                            System.out.println("released a");
                        })),
                        new DigitalTask.ActionPair(DigitalTask.Type.RELEASE, new InlineTask(() -> {
                            System.out.println("holding a");
                        }))
                ),
                new AnalogTask(
                        "joystick_left", 1,
                        new AnalogTask.ActionPair(AnalogTask.Type.HOLD, new InlineTask(() -> {
                            System.out.println("Holding down at " + gamepadEx.getAnalog("joystick_left"));
                        }))
                )
        );
    }
}

