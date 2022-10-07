package org.firstinspires.ftc.teamcode.OpModes

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name="test")
class lienar : LinearOpMode() {
    override fun runOpMode() {
        waitForStart()
        val test = hardwareMap.dcMotor.get("motorFL")
        val slider1 = hardwareMap.dcMotor.get("motorSlider")
        val slider2 = hardwareMap.dcMotor.get("motorSlider1")
        while (opModeIsActive()) {
            if (gamepad1.dpad_up) {
                slider1.power = (-1).toDouble()
                slider2.power = 1.toDouble()
            }
            if (gamepad1.dpad_down) {
                slider1.power = (1).toDouble()
                slider2.power = (-1).toDouble()
            }
            if (!gamepad1.dpad_down && !gamepad1.dpad_up) {
                slider1.power = 0.toDouble()
                slider2.power = 0.toDouble()
            }
        }
    }
}