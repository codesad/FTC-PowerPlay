package com.info1robotics.bobot.tasks

import kotlin.math.abs

//because the break zero power behaiviour on the motors doesn't produce the necessary torque to sustain the extended linkage
//we use a corrector to keep the desired position in TeleOp
class ArmCorrectorTask :Task(){
     var startPosition =0;
    override fun run() {
        startPosition=context.sliderLeft.currentPosition
       if( startPosition!=context.sliderLeft.currentPosition)
       {
           +SliderTask(.6*((startPosition-context.sliderLeft.currentPosition)/
                   abs(startPosition-context.sliderLeft.currentPosition)))
       }
        while(startPosition!=context.sliderLeft.currentPosition)
        {}
        +SliderTask(.0)
        state=State.FINISHED
    }

    override fun tick() {}

}