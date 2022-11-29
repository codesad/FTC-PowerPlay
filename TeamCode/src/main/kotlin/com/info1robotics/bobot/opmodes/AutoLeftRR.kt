package com.info1robotics.bobot.opmodes
import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.*
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.sync
import com.info1robotics.bobot.tasks.TaskBuilder.wait
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.hardware.DcMotor

@Autonomous(name="Default Auto RR")
@Config
class AutoLeftRR : AutoOpMode() {
    override val task: Task = sync {
        +SliderTask(SliderTask.Level.MID)
        + {
            drive = SampleMecanumDrive(hardwareMap)
            drive.followTrajectory(
                drive.trajectoryBuilder(Pose2d())
                    .strafeLeft(LEFT)
                    .build()
            )
        }
        + wait(300)
        + {
            drive = SampleMecanumDrive(hardwareMap)
            drive.followTrajectory(
                drive.trajectoryBuilder(Pose2d())
                    .strafeRight(RIGHT)
                    .build()
            )
        }
        + wait(300)
        + {
            drive = SampleMecanumDrive(hardwareMap)
            drive.followTrajectory(
                drive.trajectoryBuilder(Pose2d())
                    .forward(FORWARD)
                    .build()
            )
        }
        + SleepTask(300)
        + {
            println("FOUND ZONE IS $zone")
            if (zone == 1) {
                drive = SampleMecanumDrive(hardwareMap)
                drive.followTrajectory(
                    drive.trajectoryBuilder(Pose2d())
                        .strafeLeft(SECOND_LEFT)
                        .build()
                )
            } else if (zone == 3) {
                drive = SampleMecanumDrive(hardwareMap)
                drive.followTrajectory(
                    drive.trajectoryBuilder(Pose2d())
                        .strafeRight(SECOND_RIGHT)
                        .build()
                )
            }
        }
    }

    companion object {
        @JvmField
        var FORWARD = 28.0
        @JvmField
        var LEFT = 30.0
        @JvmField
        var RIGHT = 28.0
        @JvmField
        var SECOND_LEFT = 30.0
        @JvmField
        var SECOND_RIGHT = 28.0
    }
}