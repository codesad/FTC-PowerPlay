package com.info1robotics.bobot.tasks;

/**
 * Toggles the claw's state.
 */
public class ClawTask extends Task {
    public static double openPosition = 0.5;
    public static double closedPosition = 1.0;

    @Override
    public void run() {
        if (!context.clawOpen) {
            context.claw.setPosition(openPosition);
            context.clawOpen = true;
        }
        else {
            context.claw.setPosition(closedPosition);
            context.clawOpen = false;
        }
        System.out.println(context.clawOpen);
        state = State.FINISHED;
    }
}
