package com.info1robotics.bobot.tasks;

import java.util.Arrays;

/**
 * Asynchronously starts and ticks each child.
 * Ends when all children finish.
 */
public class AllTask extends CompoundTask {
    public AllTask(Task... children) {
        super(children);
    }

    @Override
    public void tick() {
        // once all children finish, stop
        if (Arrays.stream(children).allMatch(Task::isFinished)) {
            state = State.FINISHED;
            return;
        }
        for (Task child : children) {
            if (child.isRunning()) {
                child.tick();
            }
        }
    }
}
