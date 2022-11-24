package com.info1robotics.bobot.opmodes

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

@Config
@Autonomous(group = "org/firstinspires/ftc/teamcode/roadrunner/drive")
class TestAuto2 : AutoOpMode() {


        val drive = SampleMecanumDrive(hardwareMap)

        val test = drive.trajectoryBuilder(Pose2d(-60.0, 36.0))
            .lineToLinearHeading(Pose2d(-60.0, 45.0,Math.toRadians(90.0)))
            .build()
    override val task: Task=sync{
        +{
            drive.followTrajectory(test)
        }
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