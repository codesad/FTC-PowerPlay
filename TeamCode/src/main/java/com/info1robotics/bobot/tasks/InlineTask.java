package com.info1robotics.bobot.tasks;

/**
 * Runs the given block synchronously.
 * Intended for one-liners.
 */
public class InlineTask extends Task {
    private final Runnable runnable;
    public InlineTask(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
        state = State.FINISHED;
    }
}
