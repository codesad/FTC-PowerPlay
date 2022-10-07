package org.firstinspires.ftc.teamcode.tasks

class AutoBuilder {
    private val tasks = mutableListOf<Task>()

    operator fun Task.unaryPlus() {
        tasks.add(this)
    }


    companion object {
        @JvmStatic
        fun auto(init: AutoBuilder.() -> Unit): AutoBuilder {
            val builder = AutoBuilder()
            builder.init()
            return builder
        }
    }
}