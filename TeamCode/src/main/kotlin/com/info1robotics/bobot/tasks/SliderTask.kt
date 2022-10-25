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
        if (context.slider.currentPosition == level.tick) {
            context.slider.power = 0.0
            state = State.FINISHED
        }
    }

    override fun run() {
        context.slider.targetPosition = level.tick
        context.slider.power = 0.3
    }
}