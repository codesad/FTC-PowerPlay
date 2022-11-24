package com.info1robotics.bobot.opmodes

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.geometry.Vector2d
import com.acmerobotics.roadrunner.trajectory.Trajectory
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.ClawTask
import com.info1robotics.bobot.tasks.SliderTask
import com.info1robotics.bobot.tasks.Task
import com.info1robotics.bobot.tasks.TaskBuilder.async
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

@Config
@Autonomous(group = "org/firstinspires/ftc/teamcode/roadrunner/drive")
class TestAuto2 : AutoOpMode() {

        lateinit var drive: SampleMecanumDrive
        lateinit var testTrajectory: Trajectory

    override val task: Task= sync {
        +ClawTask()
        + {
            drive = SampleMecanumDrive(hardwareMap)
            drive.poseEstimate = Pose2d(36.0, -72.0, Math.toRadians(startHeading))
            testTrajectory = drive.trajectoryBuilder(Pose2d(36.0, -72.0, Math.toRadians(startHeading)))
                .lineToLinearHeading(Pose2d(X, Y, Math.toRadians(END_TANGENT)))
                .build()
        }
        +{
            drive.followTrajectory(testTrajectory)
        }
            +SliderTask(SliderTask.Level.MID)
                    +{
                        drive.followTrajectory(
                            drive.trajectoryBuilder(
                                Pose2d(X, Y, Math.toRadians(END_TANGENT))
                            )
                                .forward(5.0)
                                .build()
                        )
                    }

    }



    companion object {
        @JvmField
        var startHeading = 90.0
        @JvmField
        var END_TANGENT = 140.0
        @JvmField
        var X = 36.0
        @JvmField
        var Y = -37.0
    }
}