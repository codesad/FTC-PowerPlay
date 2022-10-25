package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

/**
 * Base class for TeleOpModes. An extension of [ImplOpMode].
 * @property task Instance of [AllTasks].
 * Runs at start and never ends. Designed for [DigitalTask], [AnalogTask].
 * @property useOmniMecanum Indicates whether joystick control should be enabled.
 * Note that moving the wheels from tasks will be impossible, as the motors are constantly changed based on the values of the joysticks.
 */
abstract class TeleOpMode: ImplOpMode() {
    abstract val task: AllTasks
    open var useOmniMecanum = false
    lateinit var gamepadEx: GamepadEx
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        rr = SampleMecanumDrive(this.hardwareMap)
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
                    -(gamepad1.left_trigger - gamepad1.right_trigger.toDouble()),
                    if (gamepad1.left_bumper) .5 else 1.0
                )
            }
            telemetry.update()
        }
        onEnd()
    }
}