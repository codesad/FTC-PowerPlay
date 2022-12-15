package com.info1robotics.bobot.OpModes.AutoOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.info1robotics.bobot.OpModes.templates.AutoOpMode;
import com.info1robotics.bobot.roadrunner.trajectorysequence.TrajectorySequence;
import com.info1robotics.bobot.tasks.AsyncTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.info1robotics.bobot.tasks.PivotTask;
import com.info1robotics.bobot.tasks.SleepTask;
import com.info1robotics.bobot.tasks.SyncTask;
import com.info1robotics.bobot.tasks.TrajectoryTask;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "Road Runner Test")
public class RoadRunnerTest extends AutoOpMode {
    public  static double START_HEADING = 90.0;
    public  static double PRELOAD_X = 32.0;
    public  static double PRELOAD_Y = -30.0;
    public  static double PRELOAD_HEADING = 180.0;
    public  static double PRELOAD_TANGENT = 120.0;
    public  static double TILE_X = 36.0;
    public  static double TILE_Y = -13.5;
    public  static double CONE_STACK_X = 60.0;
    public  static double CONE_STACK_Y = -13.5;
    public  static double CONE_STACK_X_2 = 57.0;
    public  static double CONE_STACK_Y_2 = -13.5;
    public  static double CONE_STACK_HEADING = 0.0;
    public  static double MID_X = 24.0;
    public  static double MID_Y = -18.8;
    public  static double MID_X_2 = 22.0;
    public  static double MID_Y_2 = -18.8;
    public  static double MID_HEADING = -90.0;
    public Trajectory startTrajectory;
    public Trajectory tileTrajectory;
    public Trajectory tileToStackTrajectory;
    public Trajectory stackToMidTrajectory;
    public Trajectory midToStackTrajectory;
    public Trajectory stackToMidTrajectory2;
    public Trajectory midToStackTrajectory2;
    @Override
    public void onInit() {
        drive.setPoseEstimate(new Pose2d(36.0, -72.0, Math.toRadians(START_HEADING)));
        startTrajectory = drive.trajectoryBuilder(new Pose2d(36.0, -72.0, Math.toRadians(START_HEADING)))
                .splineToLinearHeading(new Pose2d(PRELOAD_X, PRELOAD_Y, Math.toRadians(PRELOAD_HEADING)), Math.toRadians(PRELOAD_TANGENT))
                .build();
        tileTrajectory = drive.trajectoryBuilder(startTrajectory.end())
                .lineTo(new Vector2d(TILE_X, TILE_Y))
                .build();
        tileToStackTrajectory = drive.trajectoryBuilder(tileTrajectory.end())
                .lineToLinearHeading(new Pose2d(CONE_STACK_X, CONE_STACK_Y, Math.toRadians(CONE_STACK_HEADING)))
                .build();
        stackToMidTrajectory = drive.trajectoryBuilder(tileToStackTrajectory.end())
                .lineToLinearHeading(new Pose2d(MID_X, MID_Y, Math.toRadians(MID_HEADING)))
                .build();
        midToStackTrajectory = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToLinearHeading(new Pose2d(CONE_STACK_X, CONE_STACK_Y, Math.toRadians(CONE_STACK_HEADING)))
                .build();
        midToStackTrajectory2 = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToLinearHeading(new Pose2d(CONE_STACK_X_2, CONE_STACK_Y_2, Math.toRadians(CONE_STACK_HEADING)))
                .build();
        stackToMidTrajectory2 = drive.trajectoryBuilder(midToStackTrajectory2.end())
                .lineToLinearHeading(new Pose2d(MID_X_2, MID_Y_2, Math.toRadians(MID_HEADING)))
                .build();

        task = new SyncTask(
                new AsyncTask(
                    new TrajectoryTask(startTrajectory),
                    new PivotTask(PivotTask.Level.MID)
                ),
                new TrajectoryTask(tileTrajectory),
                new AsyncTask(
                    new TrajectoryTask(tileToStackTrajectory),
                    new PivotTask(PivotTask.Level.CONE_5)
                ),
                new AsyncTask(
                    new TrajectoryTask(stackToMidTrajectory),
                    new PivotTask(PivotTask.Level.MID)
                ),
                new AsyncTask(
                    new TrajectoryTask(midToStackTrajectory),
                    new PivotTask(PivotTask.Level.CONE_4)
                ),
                new AsyncTask(
                    new TrajectoryTask(stackToMidTrajectory2),
                    new PivotTask(PivotTask.Level.MID)
                )
        );
    }
}
