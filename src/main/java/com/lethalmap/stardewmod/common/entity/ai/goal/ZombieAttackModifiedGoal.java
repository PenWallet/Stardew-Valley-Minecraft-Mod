package com.lethalmap.stardewmod.common.entity.ai.goal;

import com.lethalmap.stardewmod.common.entity.IridiumBat;

public class ZombieAttackModifiedGoal extends MeleeAttackModifiedGoal {
    private final IridiumBat bat;

    public ZombieAttackModifiedGoal(IridiumBat bat, double speed, boolean useLongMemory) {
        super(bat, speed, useLongMemory);
        this.bat = bat;
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        super.startExecuting();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask() {
        super.resetTask();
        this.bat.setAggroed(false);
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        super.tick();
        if (this.attackTick < 10) {
            this.bat.setAggroed(true);
        } else {
            this.bat.setAggroed(false);
        }

    }
}