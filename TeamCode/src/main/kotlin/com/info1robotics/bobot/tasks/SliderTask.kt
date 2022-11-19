package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.
 * See [Level].
 */
class SliderTask(val powerq:Double ) : Task() {
    override fun run() {
        context.sliderRight.power=powerq
        context.sliderLeft.power=powerq

    }

    override fun tick() {
        state=State.FINISHED
    }

}

