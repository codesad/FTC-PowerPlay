package com.info1robotics.bobot.tasks

import androidx.core.math.MathUtils.clamp
import com.qualcomm.robotcore.hardware.DcMotorSimple

class LinkageTask( var power:Double,val direction:Direction):Task() {
    enum class Direction
    {
        EXTEND,
        LOWER,
        NONE
    }

    val speed = power
    override fun run() {
        if(direction == Direction.LOWER)
        { context.sliderServo.direction = DcMotorSimple.Direction.FORWARD
        context.sliderServoLeft.direction= DcMotorSimple.Direction.REVERSE}
        else if(direction == Direction.EXTEND)
        {   context.sliderServo.direction = DcMotorSimple.Direction.REVERSE
            context.sliderServoLeft.direction= DcMotorSimple.Direction.FORWARD}
        if(direction != Direction.NONE) {
            context.sliderServo.power = speed
            context.sliderServoLeft.power = speed
        } else {
            context.sliderServo.power = 0.0
            context.sliderServoLeft.power = 0.0
        }

        println("Slider servo run")
    }


}