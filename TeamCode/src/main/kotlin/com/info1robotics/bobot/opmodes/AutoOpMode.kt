package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive

import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.ServoControllerEx
import org.firstinspires.ftc.teamcode.EOCV.f41h12.AprilTagDetection_41h12
import org.openftc.apriltag.AprilTagDetection

/**
 * Base class for AutoOpModes. An extension of [ImplOpMode].
 * @property task The task to be executed. See extension functions in [TaskBuilder].
 */
abstract class AutoOpMode: ImplOpMode() {
    lateinit var drive: SampleMecanumDrive
    abstract val task: Task

    lateinit var aprilTag: AprilTagDetection_41h12
    var zone = 0;


    @Throws(InterruptedException::class)
    override fun onInitLoop() {
        aprilTag.detectZone()
        if(aprilTag.zone != 0) {
            zone = aprilTag.zone
        }
    }

    override fun runOpMode() {
        claw = hardwareMap.servo.get("claw")
        sliderServo=hardwareMap.crservo.get("linkage")
        sliderRight = hardwareMap.dcMotor.get("sliderRight")
        sliderRight.zeroPowerBehavior=DcMotor.ZeroPowerBehavior.BRAKE
        sliderRight.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderRight.mode=DcMotor.RunMode.RUN_USING_ENCODER
        sliderLeft = hardwareMap.dcMotor.get("sliderLeft")
        sliderLeft.zeroPowerBehavior=DcMotor.ZeroPowerBehavior.BRAKE
        sliderLeft.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderLeft.mode=DcMotor.RunMode.RUN_USING_ENCODER
        sliderRight.direction = DcMotorSimple.Direction.REVERSE
        claw.position = ClawTask.openPosition
        aprilTag = AprilTagDetection_41h12(this)
        mecanum = Mecanum(this.hardwareMap)
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        task.start(this)
        while (opModeIsActive() && !task.isFinished()) {
            aprilTag.detectZone()
            if(aprilTag.zone != 0) {
                zone = aprilTag.zone
            }
            println("detecting zone ${aprilTag.zone}")
            task.tick()
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}