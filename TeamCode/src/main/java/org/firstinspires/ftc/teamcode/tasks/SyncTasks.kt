package org.firstinspires.ftc.teamcode.tasks

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.tasks.Task
import org.firstinspires.ftc.teamcode.tasks.TaskState

class SyncTasks() : Task() {
    var currentTask = 0

    override fun tick() {
        children[currentTask].tick()
        if (children[currentTask].state == TaskState.FINISHED) {
            currentTask++
            if (currentTask >= children.size) {
                state = TaskState.FINISHED
                return
            }
            children[currentTask].start(this.context)
        }
    }

    override fun run() {
        if (children.isEmpty()) {
            state = TaskState.FINISHED
            return
        }
        children[0].start(this.context)
    }
}
