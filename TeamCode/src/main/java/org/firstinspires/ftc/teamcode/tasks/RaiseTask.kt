package org.firstinspires.ftc.teamcode.tasks

enum class Level(val tick: Int) {
    DEFAULT(0),
    LOW(200),
    MEDIUM(400),
    HIGH(800),
}

class RaiseTask(speed: Float, level: Level) : Task() {
    override fun run() {
        
    }
}