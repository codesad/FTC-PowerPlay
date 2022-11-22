package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.hardware.DcMotor
import org.apache.commons.math3.analysis.function.Abs
import kotlin.math.abs
import kotlin.math.max

class MoveTask(val x: Double, val y: Double, val t: Double, val power: Double, val position:Int ):Task() {
    fun stillRunning(): Boolean {
       return (abs(context.mecanum.br.currentPosition)>=position||abs(context.mecanum.br.currentPosition)>=position
                || abs(context.mecanum.br.currentPosition)>=position||abs(context.mecanum.br.currentPosition)>=position)
    }
   override fun tick() {
        while (stillRunning()){}
            context.mecanum.br.power=.0;
            context.mecanum.fr.power=.0;
            context.mecanum.bl.power=.0;
            context.mecanum.fl.power=.0;
            state=State.FINISHED;

    }

    override fun run() {

        context.mecanum.bl.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.fl.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.fr.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.br.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.fl.mode=DcMotor.RunMode.RUN_USING_ENCODER;
        context.mecanum.fr.mode=DcMotor.RunMode.RUN_USING_ENCODER
        context.mecanum.bl.mode=DcMotor.RunMode.RUN_USING_ENCODER;
        context.mecanum.br.mode=DcMotor.RunMode.RUN_USING_ENCODER
        context.mecanum.vectorMove(x,y,t,power)
        println(context.mecanum.fl.power)
        println(context.mecanum.br.power)
        println(context.mecanum.fr.power)
        println(context.mecanum.bl.power)
    }
}