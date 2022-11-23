package com.info1robotics.bobot.tasks

import java.text.ParsePosition
import kotlin.math.abs

//because the break zero power behaiviour on the motors doesn't produce the necessary torque to sustain the extended linkage
//we use a corrector to keep the desired position in TeleOp
class ArmCorrectorTask :Task(){
    companion object
    {
        var startPosition =0;
    }
    fun setStartPosition(currentPosition: Int)
    {
        startPosition=currentPosition
    }
    override fun run() {
        if( startPosition!=context.sliderLeft.currentPosition)
       {
           +SliderTask(.6*((startPosition-context.sliderLeft.currentPosition)/
                   abs(startPosition-context.sliderLeft.currentPosition)),SliderTask.Level.CURRENT_POSITION)
       }
        while(startPosition!=context.sliderLeft.currentPosition)
        {}
        +SliderTask(.0,SliderTask.Level.CURRENT_POSITION)
        state=State.FINISHED
    }

    override fun tick() {}

}