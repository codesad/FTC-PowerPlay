package com.info1robotics.bobot.EOCV

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.openftc.easyopencv.OpenCvCamera
import com.info1robotics.bobot.EOCV.ATDetectionPipe
import org.openftc.easyopencv.OpenCvCameraFactory
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName
import org.openftc.easyopencv.OpenCvCamera.AsyncCameraOpenListener
import org.openftc.easyopencv.OpenCvCameraRotation
import org.openftc.apriltag.AprilTagDetection
import com.info1robotics.bobot.EOCV.AprilTagAutonomousInitDetectionExample

@TeleOp(group = "org/firstinspires/ftc/teamcode/roadrunner/drive", name = "at detection")
class AprilTagAutonomousInitDetectionExample : LinearOpMode() {
    var camera: OpenCvCamera? = null
    var aprilTagDetectionPipeline: ATDetectionPipe? = null

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    var fx = 578.272
    var fy = 578.272
    var cx = 402.145
    var cy = 221.506

    // UNITS ARE METERS
    var tagsize = 0.166
    override fun runOpMode() {
        val cameraMonitorViewId = hardwareMap.appContext.resources.getIdentifier(
            "cameraMonitorViewId",
            "id",
            hardwareMap.appContext.packageName
        )
        camera = OpenCvCameraFactory.getInstance().createWebcam(
            hardwareMap.get(
                WebcamName::class.java, "Webcam 1"
            ), cameraMonitorViewId
        )
        aprilTagDetectionPipeline = ATDetectionPipe(tagsize, fx, fy, cx, cy)

        // CHANGE THE PIPELINE NAME TO THE DESIRED TAG FAMILY (41h12 / 36h11)
//        camera.setPipeline(aprilTagDetectionPipeline)
//        camera.openCameraDeviceAsync(object : AsyncCameraOpenListener {
//            override fun onOpened() {
//                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT)
//            }
//
//            override fun onError(errorCode: Int) {}
//        })
        telemetry.msTransmissionInterval = 50

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */while (!isStarted && !isStopRequested) {
            val currentDetections = aprilTagDetectionPipeline!!.latestDetections
            if (currentDetections.size != 0) {
                var tagFound = false
                for (tag in currentDetections) {
                    when (tag.id) {
                        18 -> {
                            tagOfInterest = tag
                            tagFound = true
                            zone = 1
                        }
                        19 -> {
                            tagOfInterest = tag
                            tagFound = true
                            zone = 2
                        }
                        17 -> {
                            tagOfInterest = tag
                            tagFound = true
                            zone = 3
                        }
                    }
                    //                    if(tag.id == ID_TAG_OF_INTEREST)
//                    {
//                        tagOfInterest = tag;
//                        tagFound = true;
//                        break;
//                    }
                }
                if (tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:")
                    tagToTelemetry(tagOfInterest)
                } else {
                    telemetry.addLine("Don't see tag of interest :(")
                    if (tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)")
                    } else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:")
                        tagToTelemetry(tagOfInterest)
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(")
                if (tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)")
                } else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:")
                    tagToTelemetry(tagOfInterest)
                }
            }
            telemetry.update()
            sleep(20)
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */if (tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n")
            tagToTelemetry(tagOfInterest)
            telemetry.update()
        } else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(")
            telemetry.update()
        }

        /* Actually do something useful */if (tagOfInterest == null) {
            /*
             * Insert your autonomous code here, presumably running some default configuration
             * since the tag was never sighted during INIT
             */
        } else {
            /*
             * Insert your autonomous code here, probably using the tag pose to decide your configuration.
             */

            // e.g.
            if (tagOfInterest!!.pose.x <= 20) {
                // do something
            } else if (tagOfInterest!!.pose.x >= 20 && tagOfInterest!!.pose.x <= 50) {
                // do something else
            } else if (tagOfInterest!!.pose.x >= 50) {
                // do something else
            }
        }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */while (opModeIsActive()) {
            sleep(20)
        }
    }

    fun tagToTelemetry(detection: AprilTagDetection?) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection!!.id))
        telemetry.addLine(
            String.format(
                "Translation X: %.2f feet",
                detection.pose.x * FEET_PER_METER
            )
        )
        telemetry.addLine(
            String.format(
                "Translation Y: %.2f feet",
                detection.pose.y * FEET_PER_METER
            )
        )
        telemetry.addLine(
            String.format(
                "Translation Z: %.2f feet",
                detection.pose.z * FEET_PER_METER
            )
        )
        telemetry.addLine(
            String.format(
                "Rotation Yaw: %.2f degrees", Math.toDegrees(
                    detection.pose.yaw
                )
            )
        )
        telemetry.addLine(
            String.format(
                "Rotation Pitch: %.2f degrees", Math.toDegrees(
                    detection.pose.pitch
                )
            )
        )
        telemetry.addLine(
            String.format(
                "Rotation Roll: %.2f degrees", Math.toDegrees(
                    detection.pose.roll
                )
            )
        )
    }

    companion object {
        // CHANGE THE PIPELINE NAME TO THE DESIRED TAG FAMILY (41h12 / 36h11)
        const val FEET_PER_METER = 3.28084
        var zone = 0
        var tagOfInterest: AprilTagDetection? = null
    }
}