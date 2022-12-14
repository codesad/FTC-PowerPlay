package com.info1robotics.bobot.OpModes.TeleOpModes;

import com.info1robotics.bobot.Common.GamepadEx;
import com.info1robotics.bobot.Common.Mecanum;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class TeleOp_BigBoss extends LinearOpMode {
    Mecanum mecanum= new Mecanum(this.hardwareMap);
    CRServo linkageLeft;
    CRServo linkageRight;
    DcMotor turela;
    Servo claw;
    GamepadEx  g1 ;
    GamepadEx g2;
    double openPosition = .55;
    double closedPosition = .95;
    double linkagePower=.0;


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        init();
        {
            mecanum= new Mecanum(this.hardwareMap);
            linkageLeft=hardwareMap.crservo.get("");
            linkageRight=hardwareMap.crservo.get("");
            linkageRight.setDirection(DcMotorSimple.Direction.REVERSE);
            turela=hardwareMap.dcMotor.get("");
            claw=hardwareMap.servo.get("");
            g1 = new GamepadEx(gamepad1);
            g2=new GamepadEx(gamepad2);
        }
        while (opModeIsActive())
        {
           mecanum.vectorMove((double)(g1.getAnalog("left_x")),
                   (double) g1.getAnalog("left_y"),
                   (double)(g1.getAnalog("right_trigger")-g1.getAnalog("left_trigger"))
                   ,.7);
           if(g2.getButtonDown("a")){
               if(claw.getPosition()==closedPosition)
               {
                   claw.setPosition(closedPosition);
               }
               else{
                   claw.setPosition(openPosition);
               }
           }
           linkagePower=(double)(g2.getAnalog("right_trigger")-g2.getAnalog("left_trigger"));
           linkageLeft.setPower(linkagePower);
           linkageRight.setPower(linkagePower);
           turela.setPower(g2.getAnalog("left_x"));
        }
    }


}
