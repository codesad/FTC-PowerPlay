package com.info1robotics.bobot.tasks

import com.info1robotics.bobot.tasks.SliderTask.Level
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.
 * See [Level].
 */
class SliderTask(private val level: Level) : Task() {
    enum class Level(val tick: Int) {
        GROUND(0),
        LOW(TODO()),
        MEDIUM(TODO()),
        HIGH(TODO()),
    }

    override fun tick() {
        if (context.sliderLeft.currentPosition == level.tick ||
            context.sliderRight.currentPosition == level.tick) {
            context.sliderLeft.power = 0.0
            context.sliderRight.power = 0.0
            state = State.FINISHED
        }
    }

    override fun run() {
        context.sliderLeft.targetPosition = level.tick
        context.sliderRight.targetPosition = level.tick
    }
}
