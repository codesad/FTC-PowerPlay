package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.hardware.DcMotor

/**
 * Base class for AutoOpModes. An extension of [ImplOpMode].
 * @property task The task to be executed. See extension functions in [TaskBuilder].
 */
abstract class AutoOpMode: ImplOpMode() {
    abstract val task: Task
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        sliderLeft = hardwareMap.dcMotor.get("sliderRight")
        sliderRight = hardwareMap.dcMotor.get("sliderRight")
        claw = hardwareMap.servo.get("claw")
        sliderLeft.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderLeft.mode = DcMotor.RunMode.RUN_TO_POSITION
        sliderRight.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderRight.mode = DcMotor.RunMode.RUN_TO_POSITION
        claw.position = 0.0
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        task.start(this)
        while (opModeIsActive() && !task.isFinished()) {
            task.tick()
            onLoop()
            telemetry.update()
        }
        onEnd()
    }
}