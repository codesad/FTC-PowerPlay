package com.info1robotics.bobot.OpModes.AutoOpModes;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.info1robotics.bobot.OpModes.templates.AutoOpMode;
import com.info1robotics.bobot.tasks.AsyncTask;
import com.info1robotics.bobot.tasks.ClawTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.info1robotics.bobot.tasks.PivotTask;
import com.info1robotics.bobot.tasks.SleepTask;
import com.info1robotics.bobot.tasks.SyncTask;
import com.info1robotics.bobot.tasks.TrajectoryTask;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Config
@Autonomous(name = "Road Runner Left")
public class RoadRunnerLeft extends AutoOpMode {
    public  static double START_HEADING = 90.0;
    public  static double PRELOAD_X = -31.6;
    public  static double PRELOAD_Y = -31.3 ;
    public  static double PRELOAD_HEADING = 0.0;
    public  static double PRELOAD_TANGENT = 75.0;
    public  static double TILE_X = -36.0;
    public  static double TILE_Y = -18.5;
    public  static double CONE_STACK_X = -56.6;
    public  static double CONE_STACK_Y = -21.0;
    public  static double MID_FORWARD = 2.85;
    public  static double CONE_STACK_HEADING = -180.0;
    public  static double MID_X = -24.5;
    public  static double MID_Y = -18.8;
    public  static double MID_HEADING = 270.0;
    public  static double PARK_1_X = -57.8;
    public  static double PARK_1_Y = -18.5;
    public  static double PARK_2_X = -36.0;
    public  static double PARK_2_Y = -18.0;
    public  static double PARK_3_X = -11.5;
    public  static double PARK_3_Y = -18.0;
    public Trajectory startTrajectory;
    public Trajectory tileTrajectory;
    public Trajectory tileToStackTrajectory;
    public Trajectory stackToMidTrajectory;
    public Trajectory midToStackTrajectory;
    public Trajectory midForwardTrajectory;
    public Trajectory midBackwardTrajectory;
    public Trajectory park1Trajectory;
    public Trajectory park2Trajectory;
    public Trajectory park3Trajectory;
    public Trajectory pushTrajectory;
    public Trajectory backTrajectory;
    public static double PUSH_FORWARD = 30.0;
    public static double PUSH_BACK = 10.0;
    public static double OFFSET_Y = 0.0;

    @Override
    public void onInit() {
        super.onInit();
        claw.setPosition(ClawTask.closedPosition);
        clawOpen = false;
        drive.setPoseEstimate(new Pose2d(-36.0, -72.0, Math.toRadians(START_HEADING)));
        startTrajectory = drive.trajectoryBuilder(new Pose2d(-36.0, -72.0, Math.toRadians(START_HEADING)))
                .splineToLinearHeading(new Pose2d(PRELOAD_X, PRELOAD_Y, Math.toRadians(PRELOAD_HEADING)), Math.toRadians(PRELOAD_TANGENT))
                .build();
        tileTrajectory = drive.trajectoryBuilder(startTrajectory.end())
                .lineTo(new Vector2d(TILE_X, TILE_Y))
                .build();
        pushTrajectory = drive.trajectoryBuilder(tileTrajectory.end())
                .lineTo(new Vector2d(TILE_X, TILE_Y + PUSH_FORWARD))
                .build();
        backTrajectory = drive.trajectoryBuilder(pushTrajectory.end())
                .lineTo(new Vector2d(TILE_X, TILE_Y - PUSH_BACK))
                .build();
        tileToStackTrajectory = drive.trajectoryBuilder(tileTrajectory.end())
                .lineToLinearHeading(new Pose2d(CONE_STACK_X, CONE_STACK_Y + OFFSET_Y, Math.toRadians(CONE_STACK_HEADING)))
                .build();
        stackToMidTrajectory = drive.trajectoryBuilder(tileToStackTrajectory.end())
                .lineToLinearHeading(new Pose2d(MID_X, MID_Y + OFFSET_Y, Math.toRadians(MID_HEADING)))
                .build();
        midForwardTrajectory = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToConstantHeading(new Vector2d(MID_X, MID_Y - MID_FORWARD + OFFSET_Y))
                .build();
        midBackwardTrajectory = drive.trajectoryBuilder(midForwardTrajectory.end())
                .lineToConstantHeading(new Vector2d(MID_X, MID_Y + OFFSET_Y))
                .build();
        midToStackTrajectory = drive.trajectoryBuilder(midBackwardTrajectory.end())
                .lineToLinearHeading(new Pose2d(CONE_STACK_X, CONE_STACK_Y + OFFSET_Y, Math.toRadians(CONE_STACK_HEADING)))
                .build();
        park1Trajectory = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToConstantHeading(new Vector2d(PARK_1_X, PARK_1_Y + OFFSET_Y))
                .build();
        park2Trajectory = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToConstantHeading(new Vector2d(PARK_2_X, PARK_2_Y + OFFSET_Y))
                .build();
        park3Trajectory = drive.trajectoryBuilder(stackToMidTrajectory.end())
                .lineToConstantHeading(new Vector2d(PARK_3_X, PARK_3_Y + OFFSET_Y))
                .build();

        task = new SyncTask(
                new SleepTask(50),
                new AsyncTask(
                        new TrajectoryTask(startTrajectory),
                        new PivotTask(PivotTask.Level.MID)
                ),
                new PivotTask(PivotTask.Level.DROP_MID),
                new ClawTask(),
                new SleepTask(200),
                new TrajectoryTask(tileTrajectory),
                new SleepTask(300),
                new TrajectoryTask(tileToStackTrajectory),
                new SleepTask(600),
                new PivotTask(PivotTask.Level.CONE_5),
                new SleepTask(600),
                new ClawTask(),
                new SleepTask(600),
                new AsyncTask(
                        new TrajectoryTask(stackToMidTrajectory),
                        new PivotTask(PivotTask.Level.MID)
                ),
                new SleepTask(300),
                new TrajectoryTask(midForwardTrajectory),
                new SleepTask(300),
                new PivotTask(PivotTask.Level.DROP_MID),
                new SleepTask(400),
                new ClawTask(),
                new SleepTask(300),
                new TrajectoryTask(midBackwardTrajectory),
                new InlineTask(() -> {
                    if (zone == 1) {
                        drive.followTrajectory(park1Trajectory);
                    } else if (zone == 2) {
                        drive.followTrajectory(park2Trajectory);
                    } else if (zone == 3) {
                        drive.followTrajectory(park3Trajectory);
                    }
                })
        );
    }
}
