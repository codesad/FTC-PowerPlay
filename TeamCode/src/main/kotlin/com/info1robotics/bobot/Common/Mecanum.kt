package com.info1robotics.bobot.Common

import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import kotlin.math.abs
import kotlin.math.max

class Mecanum(hardwareMap: HardwareMap) {
    var fl: DcMotor
    var fr: DcMotor
    var bl: DcMotor
    var br: DcMotor

    init {
        fl = hardwareMap.get(DcMotor::class.java, "motorFL")
        fr = hardwareMap.get(DcMotor::class.java, "motorFR")
        bl = hardwareMap.get(DcMotor::class.java, "motorBL")
        br = hardwareMap.get(DcMotor::class.java, "motorBR")
        br.direction = DcMotorSimple.Direction.FORWARD
        bl.direction = DcMotorSimple.Direction.FORWARD
        fl.direction = DcMotorSimple.Direction.FORWARD
        fr.direction = DcMotorSimple.Direction.REVERSE
        fl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        fr.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        bl.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        br.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    fun vectorMove(x: Double, y: Double, t: Double, power: Double) {
        val targetPower = normalize(
            doubleArrayOf(
                x + y + t,
                y - x - t,
                y - x + t,
                x + y - t
            )
        )
        fl.power = targetPower[0] * power
        fr.power = targetPower[1] * power
        bl.power = targetPower[2] * power
        br.power = targetPower[3] * power
    }

    private fun normalize(values: DoubleArray): DoubleArray {
        // Put powers in the range of -1 to 1 only if they aren't already
        // Not checking would cause us to always drive at full speed
        if (abs(values[0]) > 1 || abs(values[2]) > 1 || abs(
                values[1]
            ) > 1 || abs(values[3]) > 1
        ) {
            var max = 0.0
            max = max(abs(values[0]), abs(values[2]))
            max = max(abs(values[1]), max)
            max = max(abs(values[3]), max)
            values[0] /= max
            values[1] /= max
            values[2] /= max
            values[3] /= max
        }
        return values
    }
}