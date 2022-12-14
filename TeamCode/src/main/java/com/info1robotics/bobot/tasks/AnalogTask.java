package com.info1robotics.bobot.tasks;

import android.drm.DrmStore;
import android.util.Pair;

import com.info1robotics.bobot.Common.GamepadEx;
import com.info1robotics.bobot.OpModes.templates.TeleOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AnalogTask extends Task {
    public String button;
    public int gamepad;
    public double position = 0.0;

    public static class ActionPair {
        Type type;
        Task task;

        public ActionPair(Type type, Task task) {
            this.type = type;
            this.task = task;
        }
    }

    public enum Type {
        HOLD,
        RELEASE
    }

    public ArrayList<ActionPair> tasks = new ArrayList<>();

    public AnalogTask(String button, int gamepad, ActionPair ...children) {
        this.button = button;
        this.gamepad = gamepad;
        tasks.addAll(Arrays.asList(children));
    }

    @Override
    public void tick() {
        TeleOpMode teleOp = (TeleOpMode) context;
        tasks.forEach(pair -> {
            if (pair.task.isRunning()) pair.task.tick();
            GamepadEx gamepadEx = (gamepad == 1) ? teleOp.gamepadEx : teleOp.gamepadEx2;
            switch (pair.type) {
                case RELEASE:
                    if (position != gamepadEx.getAnalog(button) && gamepadEx.getAnalog(button) < 0.04) {
                        pair.task.start(context);
                    }
                    break;
                case HOLD:
                    if (gamepadEx.getAnalog(button) > 0.04) {
                        pair.task.start(context);
                    }
            }
            position = gamepadEx.getAnalog(button);
        });
    }
}
