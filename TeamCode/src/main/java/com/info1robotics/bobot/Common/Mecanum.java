package com.info1robotics.bobot.Common;

import static java.lang.Math.abs;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Mecanum {
    public DcMotor fl;
    public DcMotor fr;
    public DcMotor bl;
    public DcMotor br;

    public Mecanum(HardwareMap hardwareMap) {
        fl = hardwareMap.dcMotor.get("motorFL");
        fr = hardwareMap.dcMotor.get("motorFR");
        bl = hardwareMap.dcMotor.get("motorBL");
        br = hardwareMap.dcMotor.get("motorBR");
        this.br.setDirection(DcMotorSimple.Direction.REVERSE);
//        this.bl.setDirection(DcMotorSimple.Direction.FORWARD);
//        this.fl.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fr.setDirection(DcMotorSimple.Direction.REVERSE);
        this.fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void vectorMove(double x, double y, double t, double power) {
        double[] targetPower = normalize(
                new double[]{
                        x + y + t,
                        y - x - t,
                        y - x + t,
                        x + y - t
                }
        );
        fl.setPower(targetPower[0] * power);
        fr.setPower(targetPower[1] * power);
        bl.setPower(targetPower[2] * power);
        br.setPower(targetPower[3] * power);
    }

    public double[] normalize(double[] values) {
        // Put powers in the range of -1 to 1 only if they aren't already
        // Not checking would cause us to always drive at full speed
        if (abs(values[0]) > 1 || abs(values[2]) > 1 || abs(
                values[1]
        ) > 1 || abs(values[3]) > 1
        ) {
            double max = 0.0;
            max = Math.max(abs(values[0]), abs(values[2]));
            max = Math.max(abs(values[1]), max);
            max = Math.max(abs(values[3]), max);
            values[0] /= max;
            values[1] /= max;
            values[2] /= max;
            values[3] /= max;
        }
        return values;
    }
}
