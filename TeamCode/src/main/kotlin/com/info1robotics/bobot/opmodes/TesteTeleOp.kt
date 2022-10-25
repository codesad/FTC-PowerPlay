package com.info1robotics.bobot.opmodes

import com.acmerobotics.roadrunner.geometry.Pose2d
import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.DigitalTask.Type.*
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

@TeleOp(name = "dpad slider aaaa")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = true
    lateinit var fr: DcMotor

    override fun onInit() {

    }

    override val task = all {

    }
}
