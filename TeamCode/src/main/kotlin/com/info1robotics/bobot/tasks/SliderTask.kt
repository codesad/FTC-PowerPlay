package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.tasks.SliderTask.Level
import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.\
 * See [Level].
 */
class SliderTask(private val level: Level) : Task() {
    enum class Level(val tick: Int) {
        GROUND(0),
        LOW(3000),
        MEDIUM(5200),
        HIGH(7500),
    }

    override fun tick() {
        if (
            !context.sliderRight.isBusy) {
            context.sliderRight.power = 0.0
            state = State.FINISHED
        }
    }

    override fun run() {
        context.sliderRight.targetPosition=level.tick
        context.sliderRight.mode=DcMotor.RunMode.RUN_TO_POSITION
        context.sliderRight.power=1.0
    }
}
