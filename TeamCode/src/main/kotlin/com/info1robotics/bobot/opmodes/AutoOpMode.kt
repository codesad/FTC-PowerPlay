package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.Mecanum

import com.info1robotics.bobot.tasks.*
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.EOCV.f41h12.AprilTagDetection_41h12

/**
 * Base class for AutoOpModes. An extension of [ImplOpMode].
 * @property task The task to be executed. See extension functions in [TaskBuilder].
 */
abstract class AutoOpMode: ImplOpMode() {
    abstract val task: Task

    val aprilTag = AprilTagDetection_41h12(this)
    var zone = 0;


    @Throws(InterruptedException::class)
    override fun onInitLoop() {
        aprilTag.detectZone()
        if(aprilTag.zone!=0) zone = aprilTag.zone;
    }
    override fun runOpMode() {
        mecanum = Mecanum(this.hardwareMap)
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