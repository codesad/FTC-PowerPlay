package com.info1robotics.bobot.opmodes

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode

/*
* Op mode for preliminary tuning of the follower PID coefficients (located in the drive base
* classes). The robot drives back and forth in a straight line indefinitely. Utilization of the
* dashboard is recommended for this tuning routine. To access the dashboard, connect your computer
* to the RC's WiFi network. In your browser, navigate to https://192.168.49.1:8080/dash if you're
* using the RC phone or http://192.168.43.1:8080/dash if you are using the Control Hub. Once
* you've successfully connected, start the program, and your robot will begin moving forward and
* backward. You should observe the target position (green) and your pose estimate (blue) and adjust
* your follower PID coefficients such that you follow the target position as accurately as possible.
* If you are using SampleMecanumDrive, you should be tuning TRANSLATIONAL_PID and HEADING_PID.
* If you are using SampleTankDrive, you should be tuning AXIAL_P..........................................ID, CROSS_TRACK_PID, and HEADING_PID.
* These coefficients can be tuned live in dashboard.
*
* This opmode is designed as a convenient, coarse tuning for the follower PID coefficients. It
* is recommended that you use the FollowerPIDTuner opmode for further fine tuning.
*/
@Config
@Autonomous(group = "org/firstinspires/ftc/teamcode/roadrunner/drive")
class TestAuto : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val drive = SampleMecanumDrive(hardwareMap)
        val forward = drive.trajectoryBuilder(Pose2d())
            .forward(UP_DISTANCE_BETWEEN_TILES)
            .build()
        val right = drive.trajectoryBuilder(forward.end())
            .strafeRight(RIGHT_DISTANCE_BETWEEN_TILES)
            .build()
        val back = drive.trajectoryBuilder(right.end())
            .back(DOWN_DISTANCE_BETWEEN_TILES)
            .build()
        val left = drive.trajectoryBuilder(back.end())
            .strafeLeft(LEFT_DISTANCE_BETWEEN_TILES)
            .build()

        waitForStart()
        while (opModeIsActive() && !isStopRequested) {
            drive.followTrajectory(forward)
            drive.followTrajectory(right)
            drive.followTrajectory(back)
            drive.followTrajectory(left)
        }
    }

    companion object {
        @JvmField
        var UP_DISTANCE_BETWEEN_TILES = 27.0
        @JvmField
        var RIGHT_DISTANCE_BETWEEN_TILES = 33.0
        @JvmField
        var DOWN_DISTANCE_BETWEEN_TILES = 28.0
        @JvmField
        var LEFT_DISTANCE_BETWEEN_TILES = 30.0
    }
}