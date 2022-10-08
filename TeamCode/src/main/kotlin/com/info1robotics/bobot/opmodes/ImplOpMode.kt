package com.info1robotics.bobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.info1robotics.bobot.Common.Mecanum

open class ImplOpMode : LinearOpMode() {
    lateinit var mecanum: Mecanum
    open fun onInit() {}
    open fun onLoop() {}
    open fun onEnd() {}
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        onInit()
        waitForStart()
        while (opModeIsActive()) {
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}