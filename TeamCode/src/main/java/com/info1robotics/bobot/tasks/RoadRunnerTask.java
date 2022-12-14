package com.info1robotics.bobot.tasks;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.sun.tools.javac.comp.Todo;

public class RoadRunnerTask extends Task{
    public enum Places
    {
        TO_STACK_LEFT(.0,.0,.0),
        TO_STACK_RIGHT(.0,.0,.0),
        MANUAL(.0,.0,.0);
        public  double x;
        public  double y;
        public  double heading;
        Places(double x,double y,double heading) {
            this.x = x;
            this.y = y;
            this.heading=heading;
        }
    }
    public enum Templates
    {
        SPLINE,
        LINE_TO_LINEAR_HEADING,
        DEFAULT;
    }
    Templates template=Templates.DEFAULT;
    Places place=Places.MANUAL;
    public double start_x=.0;
    public double start_y=.0;
    public double start_heading=.0;
    @Override
    public void run()
    {
        Trajectory tr=context.drive.trajectoryBuilder(new Pose2d(.0,.0,.0))
                .splineTo(new Vector2d(.0, .0),.0).build();
        if(template==Templates.SPLINE)
        {
          tr= context.drive.trajectoryBuilder(new Pose2d(start_x,start_y,start_heading))
                    .splineTo(new Vector2d(place.x, place.y),place.heading).build();
        } else {}
        context.drive.followTrajectory(tr);
    }



}
