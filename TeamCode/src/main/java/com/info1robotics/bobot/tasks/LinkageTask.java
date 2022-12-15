package com.info1robotics.bobot.tasks;

public class LinkageTask extends Task{
    public enum Direction {
        EXTENDED(1.0),
        LOWERED(.00),
        MANUAL(.5);
        public final double posLeft;
        public final double posRight;
        Direction(double pos) {
            this.posLeft = pos;
            this.posRight = 1.0-pos;
        }
    }
    Direction direction=Direction.MANUAL;
    double position=this.direction.posLeft;
    double manualPos=.5;
    double posLeft;
    double posRight;
    @Override
    public void run() {
        if(direction==Direction.MANUAL)
        {
            posLeft=manualPos;
            posRight=1.0-manualPos;
        }
        else
        {
            posLeft=direction.posLeft;
            posRight=direction.posRight;
        }
//        context.linkageLeft.setPosition(posLeft);
//        context.linkageRight.setPosition(posRight);
        state=State.FINISHED;
    }
    LinkageTask(double position)
    {
        this.manualPos=position;
    }
    LinkageTask(Direction direction)
    {
        this.direction=direction;
    }

}
