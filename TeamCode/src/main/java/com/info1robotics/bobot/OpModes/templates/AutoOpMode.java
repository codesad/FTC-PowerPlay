package com.info1robotics.bobot.OpModes.templates;

import com.info1robotics.bobot.tasks.Task;

import org.firstinspires.ftc.teamcode.EOCV.f41h12.AprilTagDetection_41h12;

public abstract class AutoOpMode extends ImplOpMode {
    public Task task;
    public AprilTagDetection_41h12 aprilTag;
    public int zone = 0;

    @Override
    public void onInitLoop() {
        aprilTag.detectZone();
        if (aprilTag.getZone() != 0) {
            zone = aprilTag.getZone();
        }
    }
}
