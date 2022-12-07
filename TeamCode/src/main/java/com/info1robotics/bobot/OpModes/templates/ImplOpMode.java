package com.info1robotics.bobot.OpModes.templates;

import com.info1robotics.bobot.Common.Mecanum;
import com.info1robotics.bobot.tasks.ClawTask;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class ImplOpMode extends LinearOpMode {
    public Mecanum mecanum;
    public DcMotor sliderLeft;
    public DcMotor sliderRight;
    public CRServo sliderServo;
    public CRServo sliderServoLeft;
    public Servo claw;
    public boolean clawOpen = true;
    public float linkagePos = 0f;

    public void onInitLoop() {};
    public void onInit() {};
    public void onStart() {};
    public void onLoop() {};
    public void onEnd() {};

    public void initHardwareMap() {
        claw = hardwareMap.servo.get("claw");
        sliderServo=hardwareMap.crservo.get("linkage");
        sliderRight = hardwareMap.dcMotor.get("sliderRight");
        sliderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderLeft = hardwareMap.dcMotor.get("sliderLeft");
        sliderLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        sliderRight.setDirection(DcMotor.Direction.REVERSE);
        claw.setPosition(ClawTask.openPosition);
        mecanum = new Mecanum(this.hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        initHardwareMap();
        onInit();
        while (!isStarted()) {
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
