package com.info1robotics.bobot.tasks

class ClawTask:Task() {

public  companion object{
    val openPosition=.0
    val closedPosition=1.0
}

    override fun tick() {

        state = State.FINISHED;
    }
    override fun run()
    {
        if(!context.clawOpen) {
            context.claw.position=openPosition

        }
        else {
            context.claw.position=closedPosition
        }
        context.clawOpen = !context.clawOpen
    }
}