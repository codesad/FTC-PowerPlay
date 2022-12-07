package com.info1robotics.bobot.OpModes.templates;

import com.info1robotics.bobot.tasks.ClawTask;
import com.info1robotics.bobot.tasks.InlineTask;
import com.info1robotics.bobot.tasks.SyncTask;

public class Test extends AutoOpMode {
    @Override
    public void onInit() {
        task = new SyncTask(
                new ClawTask(),
                new InlineTask(() -> {
                    System.out.println("asd");
                })
        );
    }
}
