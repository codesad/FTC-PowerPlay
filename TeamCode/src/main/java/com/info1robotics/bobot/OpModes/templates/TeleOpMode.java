package com.info1robotics.bobot.OpModes.templates;

import androidx.annotation.CallSuper;

import com.info1robotics.bobot.Common.GamepadEx;
import com.info1robotics.bobot.tasks.AllTask;

public class TeleOpMode extends ImplOpMode {
    public AllTask task;
    public boolean useOmniMecanum = false;
    public double power = .73;
    public GamepadEx gamepadEx;
    public GamepadEx gamepadEx2;

    @CallSuper
    @Override
    public void onInit() {
        gamepadEx = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);
    }

    @CallSuper
    @Override
    public void onLoop() {
        task.tick();
        if (useOmniMecanum) {
            mecanum.vectorMove(
                    gamepad1.left_stick_x,
                    -gamepad1.left_stick_y,
                    -(gamepad1.left_trigger - gamepad1.right_trigger),
                    power
            );
        }
    }

    @Override
    public void onStart() {
        task.run();
    }
}
