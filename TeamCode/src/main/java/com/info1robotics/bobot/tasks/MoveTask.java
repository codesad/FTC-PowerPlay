package com.info1robotics.bobot.tasks;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MoveTask extends Task {
    private final double x;
    private final double y;
    private final double t;
    private final double power;
    private final int position;

    public MoveTask(double x, double y, double t, double power, int position) {
        this.x = x;
        this.y = y;
        this.t = t;
        this.power = power;
        this.position = position;
    }

    public boolean stillRunning() {
        return (Math.abs(context.mecanum.br.getCurrentPosition())>=position || Math.abs(context.mecanum.br.getCurrentPosition()) >= position
                || Math.abs(context.mecanum.br.getCurrentPosition())>=position || Math.abs(context.mecanum.br.getCurrentPosition()) >= position);
    }

    @Override
    public void tick() {
        if (stillRunning()) return;
        context.mecanum.br.setPower(0.0);
        context.mecanum.fr.setPower(0.0);
        context.mecanum.bl.setPower(0.0);
        context.mecanum.fl.setPower(0.0);
        state = State.FINISHED;
    }

    @Override
    public void run() {
        context.mecanum.br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        context.mecanum.bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        context.mecanum.fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        context.mecanum.fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        context.mecanum.vectorMove(x, y, t, power);
    }
}
