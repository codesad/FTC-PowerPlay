package com.info1robotics.bobot.tasks;

import com.info1robotics.bobot.OpModes.templates.ImplOpMode;

/**
 * Main element of the state machine.
 * It allows you to asynchronously execute code on the same thread.
 */
public abstract class Task {
    public boolean isFinished() {
        return state == State.FINISHED;
    }

    public boolean isRunning() {
        return state == State.RUNNING;
    }

    public enum State {
        DEFAULT,
        RUNNING,
        FINISHED,
    }

    public State state = State.DEFAULT;
    public ImplOpMode context;

    public void run() {};
    public void tick() {};

    public void start(ImplOpMode context) {
        this.context = context;
        state = State.RUNNING;
        run();
    }
}
