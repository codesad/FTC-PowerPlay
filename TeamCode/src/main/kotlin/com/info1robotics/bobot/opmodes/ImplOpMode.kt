package com.info1robotics.bobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

/**
 * Base class for OpModes. An extension of [LinearOpMode].
 * @property mecanum Instance of the [Mecanum] helper class. Utilised for movement.
 * @property slider Instance of the [DcMotor] class. Utilised for the raising and lowering of the slider.
 * @property claw Instance of the [Servo] class. Utilised for moving the claw.
 */
open class ImplOpMode : LinearOpMode() {
    lateinit var mecanum: Mecanum
    lateinit var sliderLeft: DcMotor
    lateinit var sliderRight: DcMotor
    lateinit var sliderServo: CRServo
    lateinit var claw: Servo
    lateinit var rr: SampleMecanumDrive

    var clawOpen=true;
    var linkagePos = 0.0
    open fun onInit() {
   sliderRight.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER

    }
    open fun onInitLoop() {

    }
    open fun onStart() {}
    open fun onLoop() {}
    open fun onEnd() {}
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        rr = SampleMecanumDrive(this.hardwareMap)
        claw = hardwareMap.servo.get("claw")
        sliderServo=hardwareMap.crservo.get("linkage")
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        onStart()
        while (opModeIsActive()) {
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}