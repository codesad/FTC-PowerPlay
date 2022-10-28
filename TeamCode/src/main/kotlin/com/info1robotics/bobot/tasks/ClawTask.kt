package com.info1robotics.bobot.tasks

class ClawTask:Task() {


    override fun tick() {

        state = State.FINISHED;
    }
    override fun run()
    {
        if(context.clawOpen)
        {
            context.claw.position=.8

        }
        else
        {
            context.claw.position=.6
        }
        context.clawOpen = !context.clawOpen
    }
}