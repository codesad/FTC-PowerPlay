package com.info1robotics.bobot.tasks;

import java.util.Arrays;

/**
 * Asynchronously starts and ticks each child.
 * Never ends.
 */
public class AllTask extends CompoundTask {
    public AllTask(Task... children) {
        super(children);
    }

    @Override
    public void tick() {
        // once all children finish, stop
        for (Task child : children) {
            if (child.isRunning()) {
                child.tick();
            } else {
                child.start(context);
            }
        }
    }

    @Override
    public void run() {
        for (Task child : children) child.start(context);
    }
}
