package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.tasks.AllTasks

abstract class TeleOpMode: ImplOpMode() {
    abstract val task: AllTasks
    open var useOmniMecanum = false
    lateinit var gamepadEx: GamepadEx
//    lateinit var slider: DcMotor
//    lateinit var claw: Servo
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
//        slider = hardwareMap.dcMotor.get("motorSlider")
//        claw = hardwareMap.servo.get("claw")
        gamepadEx = GamepadEx(gamepad1)
        onInit()
        while (!isStarted) {
            onInitLoop()
        }
        task.start(this)
        while (opModeIsActive()) {
            task.tick()
            gamepadEx.update()
            onLoop()
            if (useOmniMecanum) {
                mecanum.vectorMove(
                    gamepad1.left_stick_x.toDouble(),
                    -gamepad1.left_stick_y.toDouble(),
                    gamepad1.left_trigger - gamepad1.right_trigger.toDouble(),
                    if (gamepad1.left_bumper) .5 else 1.0
                )
            }
            telemetry.update()
        }
        onEnd()
    }
}