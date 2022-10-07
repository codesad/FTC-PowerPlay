package org.firstinspires.ftc.teamcode.OpModes

import android.util.Log
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.Common.GamepadEx
import org.firstinspires.ftc.teamcode.Common.GamepadEx.*
import org.firstinspires.ftc.teamcode.tasks.AnalogType
import org.firstinspires.ftc.teamcode.tasks.DigitalType.*
import org.firstinspires.ftc.teamcode.tasks.TeleOpBuilder
import org.firstinspires.ftc.teamcode.tasks.TeleOpBuilder.*
import org.firstinspires.ftc.teamcode.Common.GamepadEx.Digital.*
import org.firstinspires.ftc.teamcode.Common.GamepadEx.Analog.*
import org.firstinspires.ftc.teamcode.tasks.TeleOpBuilder.Companion.teleop
import org.firstinspires.ftc.teamcode.tasks.TeleOpBuilder.Companion.wait
import org.firstinspires.ftc.teamcode.tasks.TeleOpTasks

@TeleOp(name = "dpad slider")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = true
    val test = false

    override val teleOpBuilder = teleop {
        useOmniMecanum = true
        buttonAction(DPAD_UP, ONCE) {
            + {
                slider.power = 0.3
            }
        }
        buttonAction(DPAD_UP, RELEASE) {
            + {
                slider.power = 0.0
            }
        }
        buttonAction(DPAD_DOWN, ONCE) {
            + {
                slider.power = -0.3
            }
        }
        buttonAction(DPAD_DOWN, RELEASE) {
            + {
                slider.power = 0.0
            }
        }
        buttonAction(X, ONCE) {
            + {
                if (claw.position == 1.0) {
                    claw.position = 0.0
                } else {
                    claw.position = 1.0
                }
            }
        }
//        button(BUTTON) {
//            type(ONCE) {
//
//            }
//            type(RELEASE) {
//
//            }
//        }
    }
}