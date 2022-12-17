package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "djkds")
public class asd extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        DcMotor pivotLeft = hardwareMap.dcMotor.get("sliderLeft");
        DcMotor pivotRight = hardwareMap.dcMotor.get("sliderRight");
        pivotLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        while (opModeIsActive()) {
            System.out.println(pivotLeft.getCurrentPosition() + "left");
            System.out.println(pivotRight.getCurrentPosition() + "right");
        }
    }
}
