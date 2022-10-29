package com.info1robotics.bobot.EOCV

import android.os.SystemClock
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvCamera
import org.openftc.easyopencv.OpenCvCameraFactory
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.openftc.easyopencv.OpenCvCamera.AsyncCameraOpenListener
import org.openftc.easyopencv.OpenCvCameraRotation

open class ATDetection(var opMode: LinearOpMode) {
    private val fx = 578.272
    private val fy = 578.272
    private val cx = 402.145
    private val cy = 221.506
    private val tagSize = 0.166
    private var zone = 0
    private var tagInView = 0
    private var parkZone = ""
    val camera: OpenCvCamera
    private val aprilTagDetectionPipeline: ATDetectionPipe

    init {
        val cameraMonitorViewId = opMode.hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            opMode.hardwareMap.appContext.packageName
        )
        camera = OpenCvCameraFactory.getInstance().createWebcam(
            opMode.hardwareMap.get(
                WebcamName::class.java, "Webcam 1"
            ), cameraMonitorViewId
        )
        aprilTagDetectionPipeline = ATDetectionPipe(tagSize, fx, fy, cx, cy)

        camera.setPipeline(aprilTagDetectionPipeline)
        camera.openCameraDeviceAsync(object : AsyncCameraOpenListener {
            override fun onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT)
            }

            override fun onError(errorCode: Int) {}
        })
        opMode.telemetry.msTransmissionInterval = 50
    }

    fun detectZone(): Int {
        val currentDetections = aprilTagDetectionPipeline.latestDetections
        println("the length is ${aprilTagDetectionPipeline.latestDetections}")
        if (currentDetections.isNotEmpty()) {
            currentDetections.forEach {tag ->
                tagInView = tag.id
                when (tag.id) {
                    69 -> zone = 1
                    140 -> zone = 2
                    420 -> zone = 3
                }
            }
        }

        SystemClock.sleep(100)

        return zone
    }
}