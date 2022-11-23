package com.info1robotics.bobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@Autonomous(name = "Slider Auto Test")
class SliderAuto : LinearOpMode() {
    override fun runOpMode() {
        waitForStart()
        val sliderLeft = hardwareMap.dcMotor.get("sliderLeft")
        val sliderRight = hardwareMap.dcMotor.get("sliderRight")
        sliderRight.direction = DcMotorSimple.Direction.REVERSE
        sliderLeft.power = 0.7
        sliderRight.power = 0.7
        while (opModeIsActive()) {

        }
    }
}