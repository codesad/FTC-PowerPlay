package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "djkds")
public class asd extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        new Thread(() -> {
            sleep(2000);
            // raise linkage
        }).start();
        // follow trajectory
    }
}
