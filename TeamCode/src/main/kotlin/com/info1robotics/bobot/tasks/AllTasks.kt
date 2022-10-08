package com.info1robotics.bobot.tasks

class AllTasks : Task() {
    override fun tick() {
        children.forEach { it.tick() }
    }

    override fun run() {
        children.forEach { it.start(this.context) }
    }
}