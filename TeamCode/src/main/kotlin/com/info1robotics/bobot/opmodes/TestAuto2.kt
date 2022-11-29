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
    lateinit var testTrajectory: Trajectory
    lateinit var coneStackTrajectory: Trajectory
    lateinit var stackToMidTrajectory: Trajectory

    var parkZone = 3


    override val task: Task= sync {
        + ClawTask()
        + wait(200)
        + SliderTask(SliderTask.Level.MID)
        + wait(400)
        + { parkZone = aprilTag.zone }
        + wait(200)
        + {
            drive = SampleMecanumDrive(hardwareMap)
            drive.poseEstimate = Pose2d(36.0, -72.0, Math.toRadians(START_HEADING))
            testTrajectory = drive.trajectoryBuilder(Pose2d(36.0, -72.0, Math.toRadians(START_HEADING)))
                .splineToLinearHeading(Pose2d(PRELOAD_X, PRELOAD_Y, Math.toRadians(END_HEADING)), Math.toRadians(END_TANGENT))
                .build()
            drive.followTrajectory(testTrajectory)
        }
        + wait(300)
        + ClawTask()
        for (i in 1..2) {
            + wait(1650)
            + {
                coneStackTrajectory = drive.trajectoryBuilder(Pose2d(PRELOAD_X, PRELOAD_Y, Math.toRadians(END_HEADING)))
                    .lineToLinearHeading(Pose2d(CONE_STACK_X, CONE_STACK_Y, Math.toRadians(CONE_STACK_HEADING)))
                    .build()
                drive.followTrajectory(coneStackTrajectory)
            }
            + wait(300)
            + SliderTask((6 - i) * 40)
            + wait(1000)
            + ClawTask()
            + wait(300)
            + SliderTask(SliderTask.Level.MID)
            + {
                stackToMidTrajectory = drive.trajectoryBuilder(Pose2d(CONE_STACK_X, CONE_STACK_Y, Math.toRadians(CONE_STACK_HEADING))
                )
                    .lineToLinearHeading(Pose2d(PRELOAD_X, PRELOAD_Y, Math.toRadians(START_HEADING)))
                    .build()
                drive.followTrajectory(stackToMidTrajectory)
            }
            + wait(300)
            + ClawTask()
        }
        + {
            var parkTrajectory = drive.trajectoryBuilder(Pose2d(PRELOAD_X, PRELOAD_Y))
                .lineToLinearHeading(Pose2d(0.0, 0.0, 90.0))
                .build()
            when(parkZone) {
                1 -> parkTrajectory = drive.trajectoryBuilder(Pose2d(PRELOAD_X, PRELOAD_Y))
                    .lineToLinearHeading(Pose2d(12.0, -21.0, 90.0))
                    .build()
                3 -> parkTrajectory = drive.trajectoryBuilder(Pose2d(PRELOAD_X, PRELOAD_Y))
                    .lineToLinearHeading(Pose2d(60.0, -21.0, 90.0))
                    .build()
            }
            drive.followTrajectory(parkTrajectory)
        }
    }



    companion object {
        @JvmField
        var START_HEADING = 90.0
        @JvmField
        var END_HEADING = 210.0
        @JvmField
        var END_TANGENT = 120.0
        @JvmField
        var PRELOAD_X = 32.0
        @JvmField
        var PRELOAD_Y = -25.0

        @JvmField
        var CONE_STACK_X = 60.0
        @JvmField
        var CONE_STACK_Y = -16.5
        @JvmField
        var CONE_STACK_HEADING = 0.0
        @JvmField
        var CONE_STACK_TANGENT = 0.0
    }
}