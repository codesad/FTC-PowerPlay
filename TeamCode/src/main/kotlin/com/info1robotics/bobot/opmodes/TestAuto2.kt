package com.info1robotics.bobot.opmodes

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

@Config
@Autonomous(group = "org/firstinspires/ftc/teamcode/roadrunner/drive")
class TestAuto2 : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive = SampleMecanumDrive(hardwareMap)
        drive.poseEstimate = Pose2d(-60.0, 36.0)
        val test = drive.trajectoryBuilder(Pose2d(-60.0, 36.0))
            .splineTo(Vector2d(X, Y), Math.toRadians(END_TANGENT))
            .build()
        waitForStart()
        drive.followTrajectory(test)
    }

    companion object {
        @JvmField
        var END_TANGENT = -45.0
        @JvmField
        var X = 0.0
        @JvmField
        var Y = 0.0
    }
}