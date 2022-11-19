package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx
import com.info1robotics.bobot.Common.Mecanum
import com.info1robotics.bobot.roadrunner.drive.SampleMecanumDrive
import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
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
    var power = .73
    val linearPower=.7
    lateinit var gamepadEx: GamepadEx
    lateinit var gamepadEx2: GamepadEx
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
//        rr = SampleMecanumDrive(this.hardwareMap)
        claw = hardwareMap.servo.get("claw")
        sliderServo=hardwareMap.crservo.get("linkage")

        sliderRight = hardwareMap.dcMotor.get("sliderRight")
        sliderRight.zeroPowerBehavior=DcMotor.ZeroPowerBehavior.BRAKE
        sliderRight.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderRight.mode=DcMotor.RunMode.RUN_USING_ENCODER
        sliderLeft = hardwareMap.dcMotor.get("sliderLeft")
        sliderLeft.zeroPowerBehavior=DcMotor.ZeroPowerBehavior.BRAKE
        sliderLeft.mode=DcMotor.RunMode.STOP_AND_RESET_ENCODER
        sliderLeft.mode=DcMotor.RunMode.RUN_USING_ENCODER
        sliderRight.direction = DcMotorSimple.Direction.REVERSE
        claw.position = ClawTask.openPosition
        gamepadEx = GamepadEx(gamepad1)
        gamepadEx2 = GamepadEx(gamepad2)
        onInit()
        while (!isStarted) {
            onInitLoop()
        }

        task.start(this)
        while (opModeIsActive()) {
            task.tick()
            gamepadEx.update()
            gamepadEx2.update()
            onLoop()
            if (useOmniMecanum) {
                mecanum.vectorMove(
                    gamepad1.left_stick_x.toDouble(),
                    -gamepad1.left_stick_y.toDouble(),
                    -(gamepad1.left_trigger - gamepad1.right_trigger.toDouble()),
                    power
                )
            }
        }
        onEnd()
    }
}