package com.info1robotics.bobot.OpModes.templates;

import androidx.annotation.CallSuper;

import com.info1robotics.bobot.tasks.Task;

import org.firstinspires.ftc.teamcode.EOCV.f41h12.AprilTagDetection_41h12;

public abstract class AutoOpMode extends ImplOpMode {
    public Task task;
    public AprilTagDetection_41h12 aprilTag;
    public int zone = 0;

    @Override
    @CallSuper
    public void onStart() {
        task.start(this);
    }

    @Override
    public void initHardwareMap() {
        aprilTag = new AprilTagDetection_41h12(this);
        super.initHardwareMap();
    }

    @Override
    public void onInitLoop() {
        System.out.println("init loop");
        aprilTag.detectZone();
        if (aprilTag.getZone() != 0) {
            zone = aprilTag.getZone();
        }
    }

    @Override
    public void onLoop() {
        task.tick();
    }
}
