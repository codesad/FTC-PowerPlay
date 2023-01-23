package com.info1robotics.bobot.OpModes.AutoOpModes;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.info1robotics.bobot.OpModes.templates.AutoOpMode;
import com.info1robotics.bobot.tasks.InlineTask;
import com.info1robotics.bobot.tasks.SyncTask;
import com.info1robotics.bobot.tasks.TrajectoryTask;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class ParkErcast extends AutoOpMode {
    public Trajectory trajectoryAlign;
    public Trajectory trajectoryPark1;
    public Trajectory trajectoryPark3;
    public  static double START_HEADING = 90.0;
    public  static double PARK_1_X = -57.8;
    public  static double PARK_1_Y = -18.5;
    public  static double ALIGN_X = -36.0;
    public  static double ALIGN_Y = -18.0;
    public  static double PARK_3_X = -11.5;
    public  static double PARK_3_Y = -18.0;
    @Override
    public void onInit() {
        super.onInit();
        drive.setPoseEstimate(new Pose2d(-36.0, -72.0, Math.toRadians(START_HEADING)));
        trajectoryAlign = drive.trajectoryBuilder(new Pose2d(-36.0, -72.0))
                .lineToConstantHeading(new Vector2d(ALIGN_X, ALIGN_Y))
                .build();
        trajectoryPark1 = drive.trajectoryBuilder(trajectoryAlign.end())
                .lineToConstantHeading(new Vector2d(PARK_1_X, PARK_1_Y))
                .build();
        trajectoryPark3 = drive.trajectoryBuilder(trajectoryAlign.end())
                .lineToConstantHeading(new Vector2d(PARK_3_X, PARK_3_Y))
                .build();

        task = new SyncTask(
                new TrajectoryTask(trajectoryAlign),
                new InlineTask(() -> {
                    if (zone == 1) drive.followTrajectory(trajectoryPark1);
                    else if (zone == 3) drive.followTrajectory(trajectoryPark3);
                })
        );
    }
}

