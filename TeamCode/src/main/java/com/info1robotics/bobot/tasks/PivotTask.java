package com.info1robotics.bobot.tasks;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
public class PivotTask extends Task {
    public enum Level {
        GROUND(0),
        LOW(1200),
        MID(1065),
        DROP_MID(960),
        HIGH(2300),
        CONE_5(195),
        CONE_4(80),
        MANUAL(14821234);
        public final int tick;
        Level(int tick) {
            this.tick = tick;
        }
    }
    private Level level = Level.MANUAL;
    private double power = .55;
    private int desiredPosition = 3;
    @Override
    public void run() {
        if(level == Level.MANUAL) {
            context.pivotLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            context.pivotRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } else {
            context.pivotLeft.setTargetPosition(level.tick);
            context.pivotRight.setTargetPosition(level.tick);
            context.pivotLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            context.pivotRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        context.pivotRight.setPower(power);
        context.pivotLeft.setPower(power);
    }
    @Override
    public void tick() {
        if (level == Level.MANUAL) {
            state = State.FINISHED;
        } else if (!context.pivotRight.isBusy() || !context.pivotLeft.isBusy()) {
//            context.pivotRight.setPower(.0);
//            context.pivotLeft.setPower(.0);
            state = State.FINISHED;
        }
    }
    public PivotTask(double power){
        this.power = power;
    }
    public PivotTask(Level level,double power){
        desiredPosition = level.tick;
        this.level = level;
        this.power = power;
    }
    public PivotTask(Level level) {
        desiredPosition = level.tick;
        this.level = level;
    }
    public PivotTask(int position){
        this.desiredPosition = position;
    }
    public PivotTask(int position, double power){
        this.desiredPosition = position;
        this.power = power;
    }
}
