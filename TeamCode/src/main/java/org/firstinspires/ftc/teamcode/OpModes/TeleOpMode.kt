package org.firstinspires.ftc.teamcode.OpModes

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Common.GamepadEx
import org.firstinspires.ftc.teamcode.Common.Mecanum
import org.firstinspires.ftc.teamcode.tasks.TeleOpBuilder
import kotlin.math.*

abstract class TeleOpMode: ImplOpMode() {
    abstract val teleOpBuilder: TeleOpBuilder
    open var useOmniMecanum = false
    lateinit var gamepadEx: GamepadEx
    lateinit var slider: DcMotor
    lateinit var claw: Servo
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
        slider = hardwareMap.dcMotor.get("motorSlider")
        claw = hardwareMap.servo.get("claw")
        gamepadEx = GamepadEx(gamepad1)
        onInit()
        waitForStart()
        val teleOp = teleOpBuilder.build(gamepadEx)
        teleOp.start(this)
        while (opModeIsActive()) {
            teleOp.tick()
            gamepadEx.update()
            onLoop()
            if (useOmniMecanum) {
                val r = hypot(gamepad1.left_stick_x.toDouble(), gamepad1.left_stick_y.toDouble())
                val robotAngle = atan2(gamepad1.left_stick_y.toDouble(), gamepad1.left_stick_x.toDouble()) - PI / 4
                val rightX = gamepad1.right_stick_x.toDouble()
                val v1 = r * cos(robotAngle) + rightX
                val v2 = r * sin(robotAngle) - rightX
                val v3 = r * sin(robotAngle) + rightX
                val v4 = r * cos(robotAngle) - rightX

                mecanum.fl.power = v1
                mecanum.fr.power = v2
                mecanum.bl.power = v3
                mecanum.br.power = v4
            }
            telemetry.update()
        }
        onEnd()
    }
}