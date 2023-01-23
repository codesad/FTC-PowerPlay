package com.info1robotics.bobot.OpModes.templates;

import androidx.annotation.CallSuper;

import com.info1robotics.bobot.Common.Mecanum;
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class ImplOpMode extends LinearOpMode {
    public Mecanum mecanum;
    public DcMotor pivotLeft;
    public DcMotor pivotRight;
    public CRServo linkageRight;
    public CRServo linkageLeft;
    public Servo claw;
    public boolean clawOpen = true;
    public float linkagePos = 0f;
    public SampleMecanumDrive drive;

    public void onInitLoop() {}
    @CallSuper
    public void onInit() {
        initHardwareMap();
    }
    public void onStart() {}
    public void onLoop() {}
    public void onEnd() {}

    public void initHardwareMap() {
        pivotLeft = hardwareMap.dcMotor.get("sliderLeft");
        pivotRight = hardwareMap.dcMotor.get("sliderRight");
        linkageLeft = hardwareMap.crservo.get("linkageLeft");
        linkageRight = hardwareMap.crservo.get("linkageRight");
        pivotRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotLeft.setDirection(DcMotor.Direction.REVERSE);
        claw = hardwareMap.servo.get("claw");
//        claw.setPosition(ClawTask.openPosition);
        drive = new SampleMecanumDrive(this.hardwareMap);
        mecanum = new Mecanum(this.hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        initHardwareMap();
        onInit();
        while (!isStarted() && !isStopRequested()) {
            onInitLoop();
        }
        onStart();
        while (opModeIsActive()) {
            onLoop();
            telemetry.update();
        }
        onEnd();
    }
}
