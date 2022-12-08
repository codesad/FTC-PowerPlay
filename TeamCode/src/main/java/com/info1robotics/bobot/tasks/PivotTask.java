package com.info1robotics.bobot.tasks;

public class PivotTask extends Task {
    public enum Level {
        GROUND(0),
        LOW(1200),
        MID(2000),
        HIGH(2300),
        MANUAL(14821234);
        public final int tick;
        private Level(int tick) {
            this.tick = tick;
        }
    }
    private Level level=Level.MANUAL;
    private double power=.8;
    private int desiredPosition=3;
    @Override
    public void run(){

        context.pivotRight.setPower(power);
        context.pivotLeft.setPower(power);

    }
    @Override
    public void tick(){
        if(level==Level.MANUAL)
        {
            state=State.FINISHED;
        }
        else
        {
            if(context.pivotRight.getCurrentPosition()==desiredPosition||
                    context.pivotLeft.getCurrentPosition()==desiredPosition)
            {
                context.pivotRight.setPower(.0);
                context.pivotLeft.setPower(.0);
                state=State.FINISHED;
            }
        }
    }
    PivotTask(double power){
        this.power=power;
    }
    PivotTask(Level level,double power){
        desiredPosition= level.tick;
        this.level=level;
        this.power=power;
    }
    PivotTask(Level level){
        desiredPosition= level.tick;
        this.level=level;
    }
    PivotTask(int position){
        this.desiredPosition=position;
    }
    PivotTask(int position,double power){
        this.desiredPosition=position;
        this.power=power;
    }
}
