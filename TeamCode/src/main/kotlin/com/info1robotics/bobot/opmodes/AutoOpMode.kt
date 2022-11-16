package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.EOCV.ATDetection
import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * Base class for AutoOpModes. An extension of [ImplOpMode].
 * @property task The task to be executed. See extension functions in [TaskBuilder].
 */
abstract class AutoOpMode: ImplOpMode() {
    abstract val task: Task
    lateinit var atDetection: ATDetection
    var parkzone = 1

    @Throws(InterruptedException::class)
    override fun onInitLoop() {
        parkzone=atDetection.detectZone()
    }
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        claw = hardwareMap.servo.get("claw")
        sliderRight = hardwareMap.dcMotor.get("sliderRight")
        sliderRight.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderRight.mode=DcMotor.RunMode.RUN_USING_ENCODER
        atDetection = ATDetection(this)
        claw.position = 0.8
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        task.start(this)
        while (opModeIsActive() && !task.isFinished()) {
            atDetection.detectZone()
            task.tick()
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}