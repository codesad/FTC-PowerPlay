package com.info1robotics.bobot.tasks;

import java.util.concurrent.Callable;

/**
 * Waits while the given condition is true.
 * Unless used in a synchronous task, this will not block the thread of execution.
 * @param condition Lambda that returns true if the task should continue waiting.
 */
public class SleepWhile extends Task {
    Callable<Boolean> condition;

    public SleepWhile(Callable<Boolean> condition) {
        this.condition = condition;
    }

    @Override
    public void tick() {
        try {
            if (!condition.call()) {
                state = State.FINISHED;
            }
        } catch (Exception ignored) {}
    }
}
