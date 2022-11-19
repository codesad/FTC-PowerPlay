package com.info1robotics.bobot.tasks

import androidx.core.math.MathUtils.clamp
import com.qualcomm.robotcore.hardware.DcMotorSimple

class LinkageTask(val direction:Direction):Task() {
    enum class Direction
    {
        EXTEND,
        LOWER,
        NONE
    }

    val speed = 0.3
    override fun run() {
        if(direction == Direction.EXTEND)
            context.sliderServo.direction = DcMotorSimple.Direction.FORWARD
        else if(direction == Direction.LOWER)
            context.sliderServo.direction = DcMotorSimple.Direction.REVERSE

        if(direction != Direction.NONE)
            context.sliderServo.power = speed
        else
            context.sliderServo.power = 0.0

        println("Slider servo run")
    }


}