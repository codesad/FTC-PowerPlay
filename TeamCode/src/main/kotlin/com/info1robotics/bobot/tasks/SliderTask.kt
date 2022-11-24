package com.info1robotics.bobot.tasks

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.logging.Level
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.
 * See [Level].
 */
@Config
class SliderTask( var level:Level) : Task() {
    companion object
    {
        public var positionTick =1;
        private var currentSlider=1;
    }
    enum class Level(var tick:Int)
    {
        GROUND(0),
        LOW(500),
        MID(1200),
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
      context.sliderLeft.targetPosition=level.tick
       context.sliderRight.targetPosition=level.tick
        context.sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        context.sliderLeft.mode=DcMotor.RunMode.RUN_TO_POSITION
        context.sliderRight.power=.8
        context.sliderLeft.power=.8
    }

    override fun tick() {
        if (!context.sliderRight.isBusy && !context.sliderLeft.isBusy)
        {
            context.sliderRight.power=.0
            context.sliderLeft.power=.0
            state=State.FINISHED

        }

    }
   }

