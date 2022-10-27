package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.hardware.DcMotor

class MoveTask(val x: Double, val y: Double, val t: Double, val power: Double, val position:Int ):Task() {
   override fun tick() {
       println(context.mecanum.br.currentPosition)
       println(context.mecanum.fl.currentPosition)
       println(context.mecanum.bl.currentPosition)
       println(context.mecanum.fr.currentPosition)
        if (!context.mecanum.br.isBusy || !context.mecanum.fr.isBusy
            || !context.mecanum.bl.isBusy || !context.mecanum.fl.isBusy)
        {
            context.mecanum.br.power=.0;
            context.mecanum.fr.power=.0;
            context.mecanum.bl.power=.0;
            context.mecanum.fl.power=.0;
            state=State.FINISHED;
        }
    }

    override fun run() {
        context.mecanum.bl.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.fl.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.fr.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.br.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        context.mecanum.bl.targetPosition=position;
        println(context.mecanum.bl.targetPosition)
        context.mecanum.fl.targetPosition=position;
        println(context.mecanum.fl.targetPosition)
        context.mecanum.br.targetPosition=position;
        println(context.mecanum.br.targetPosition)
        context.mecanum.fr.targetPosition=position;
        println(context.mecanum.fr.targetPosition)
        context.mecanum.bl.mode=DcMotor.RunMode.RUN_TO_POSITION
        println(context.mecanum.bl.currentPosition)
        context.mecanum.fl.mode=DcMotor.RunMode.RUN_TO_POSITION
        println(context.mecanum.fl.currentPosition)
        context.mecanum.fr.mode=DcMotor.RunMode.RUN_TO_POSITION
        println(context.mecanum.fr.currentPosition)
        context.mecanum.br.mode=DcMotor.RunMode.RUN_TO_POSITION
        println(context.mecanum.br.currentPosition)
        context.mecanum.vectorMove(x,y,t,power)

    }
}