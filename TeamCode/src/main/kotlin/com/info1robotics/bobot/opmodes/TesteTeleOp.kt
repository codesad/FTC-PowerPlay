package com.info1robotics.bobot.opmodes

import com.info1robotics.bobot.Common.GamepadEx.Digital.*
import com.info1robotics.bobot.tasks.DigitalTask.Type.PRESS
import com.info1robotics.bobot.tasks.DigitalTask.Type.RELEASE
import com.info1robotics.bobot.tasks.TaskBuilder.all
import com.info1robotics.bobot.tasks.TaskBuilder.digital
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp(name = "Movement")
class TesteTeleOp : TeleOpMode() {
    override var useOmniMecanum = true

    override val task = all {
        + digital(DPAD_UP) {
            on(PRESS) {
                + {
                    sliderLeft.power = .5
                    sliderRight.power = .5
                }
            }
            on(RELEASE) {
                + {
                    sliderLeft.power = .0
                    sliderRight.power = .0
                }
            }
        }

        + digital(DPAD_DOWN) {
            on(PRESS) {
                + {
                    sliderLeft.power = -.5
                    sliderRight.power = -.5
                }
            }
            on(RELEASE) {
                + {
                    sliderLeft.power = .0
                    sliderRight.power = .0
                }
            }
        }

        +digital(X) {
            on(PRESS) {
                + {
                    sliderLeft.power = .3
                }
            }
            on(RELEASE) {
                + {
                    sliderLeft.power = .0
                }
            }
        }
//        +digital(B) {
//            on(PRESS) {
//                + {
//                    sliderLeft.power = -.3
//                }
//            }
//            on(RELEASE) {
//                + {
//                    sliderLeft.power = .0
//                }
//            }
//        }
//        +digital(Y) {
//            on(PRESS) {
//                + {
//                    sliderRight.power = .3
//                }
//            }
//            on(RELEASE) {
//                + {
//                    sliderRight.power = .0
//                }
//            }
//        }
//        +digital(A) {
//            on(PRESS) {
//                + {
//                    sliderRight.power = -.3
//                }
//            }
//            on(RELEASE) {
//                + {
//                    sliderRight.power = .0
//                }
//            }
//        }
        +digital(Y) {
            on(PRESS) {
                + {
                    claw.position = 0.7
                }
            }
        }

        +digital(A) {
            on(PRESS) {
                +{
                    claw.position = 0.0
                }
            }
        }
    }
}
