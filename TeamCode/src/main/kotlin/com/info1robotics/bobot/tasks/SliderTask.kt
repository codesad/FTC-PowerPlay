package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.hardware.DcMotor
import java.util.logging.Level
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.
 * See [Level].
 */
class SliderTask(val power:Double,var level:Level ) : Task() {
    companion object
    {
        public var positionTick =1;
        private var currentSlider=1;
    }
    enum class Level(var tick:Int)
    {
        GROUND(0),
        LOW(0),
        MID(0),
        HIGH(0),
        MANUAL_DOWN(0),
        MANUAL_UP(1000000000),
        TO_POSITION(positionTick),
        CURRENT_POSITION(currentSlider)
    }
    fun setLevelPosition( position:Int)
    {
        positionTick=position;
    }
    fun setCurrent()
    {
        currentSlider=context.sliderLeft.currentPosition
    }

    override fun run() {
        setCurrent()
        context.sliderLeft.targetPosition=level.tick
        context.sliderRight.targetPosition=level.tick
        context.sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        context.sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        context.sliderRight.power=power
        context.sliderLeft.power=power
        while (context.sliderRight.isBusy&&context.sliderLeft.isBusy)
        {}
        context.sliderRight.power=.0
        context.sliderLeft.power=.0
        state=State.FINISHED
    }
   }

