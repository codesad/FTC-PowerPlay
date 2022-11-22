package com.info1robotics.bobot.tasks

import com.qualcomm.robotcore.hardware.DcMotor
import kotlin.math.abs

/**
 * Raises or lowers the slider according to the given level.
 * See [Level].
 */
class SliderTask(val power:Double ) : Task() {
    override fun run() {
        context.sliderRight.power=power
        context.sliderLeft.power=power

    }

    override fun tick() {
        state=State.FINISHED
    }

}

