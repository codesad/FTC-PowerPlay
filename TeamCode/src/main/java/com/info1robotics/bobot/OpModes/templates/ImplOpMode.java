package com.info1robotics.bobot.OpModes.templates;

import com.info1robotics.bobot.Common.Mecanum;
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive;
import com.info1robotics.bobot.tasks.ClawTask;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class ImplOpMode extends LinearOpMode {
    public Mecanum mecanum;
    public DcMotor pivotLeft;
    public DcMotor pivotRight;
    public DcMotor sliderRight;
    public Servo linkageRight;
    public Servo linkageLeft;
    public Servo claw;
    public boolean clawOpen = true;
    public float linkagePos = 0f;
    public SampleMecanumDrive drive= new SampleMecanumDrive(this.hardwareMap);

    public void onInitLoop() {}
    public void onInit() {
        initHardwareMap();
    }
    public void onStart() {}
    public void onLoop() {}
    public void onEnd() {}

    public void initHardwareMap() {
        sliderRight = hardwareMap.dcMotor.get("sliderRight");
        pivotLeft = hardwareMap.dcMotor.get("sliderLeft");
        linkageLeft = hardwareMap.servo.get("linkageLeft");
        linkageRight = hardwareMap.servo.get("linkageRight");
        sliderRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sliderRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        sliderRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pivotLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pivotLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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
