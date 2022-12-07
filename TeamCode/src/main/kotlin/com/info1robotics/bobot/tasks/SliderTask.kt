package com.info1robotics.bobot.tasks

import com.acmerobotics.dashboard.config.Config
import com.qualcomm.robotcore.hardware.DcMotor
import java.util.logging.Level
import kotlin.math.abs

/**
 * Ra   ises or lowers the slider according to the given level.
 * See [Level].
 */
@Config
class SliderTask(var level: Level) : Task() {
    companion object {
        public var positionTick = 1;
        private var currentSlider = 1;
    }

    constructor(position: Int): this(Level.TO_POSITION) {
        setLevelPosition(position)
    }

    enum class Level(var tick: Int) {
        GROUND(0),
        LOW(500),
        MID(1200),
        HIGH(0),
        MANUAL_DOWN(0),
        MANUAL_UP(1000000000),
        TO_POSITION(positionTick),
        CURRENT_POSITION(currentSlider)
    }

    fun setLevelPosition(position: Int) {
        positionTick = position;
    }

    fun setCurrent() {
        currentSlider = context.sliderLeft.currentPosition
    }

    override fun run() {
        context.sliderLeft.targetPosition = level.tick
        context.sliderRight.targetPosition = level.tick
        context.sliderRight.power = .9
        context.sliderLeft.power = .9
        context.sliderLeft.mode = DcMotor.RunMode.RUN_TO_POSITION
        context.sliderLeft.mode = DcMotor.RunMode.RUN_TO_POSITION
    }

    override fun tick() {
        println("state: $state")
//        println("left: " + context.sliderLeft.currentPosition)
//        println("right: " + context.sliderRight.currentPosition)
//        println("error: $error")
//        if ((!context.sliderRight.isBusy || !context.sliderLeft.isBusy)) {
        if (context.sliderLeft.currentPosition == context.sliderLeft.targetPosition ||
            context.sliderRight.currentPosition == context.sliderRight.targetPosition) {
            println(context.sliderLeft.isBusy)
            println(context.sliderRight.isBusy)
            context.sliderRight.power = .0
            context.sliderLeft.power = .0
            state = State.FINISHED
        }
    }
}

