package com.info1robotics.bobot.opmodes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.info1robotics.bobot.Common.Mecanum

open class ImplOpMode : LinearOpMode() {
    lateinit var mecanum: Mecanum
    open fun onInit() {}
    open fun onInitLoop() {}
    open fun onStart() {}
    open fun onLoop() {}
    open fun onEnd() {}
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        onStart()
        while (opModeIsActive()) {
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}