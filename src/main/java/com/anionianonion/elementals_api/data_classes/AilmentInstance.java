package com.anionianonion.elementals_api.data_classes;

import com.anionianonion.elementals_api.api.ElementalsAPI;

//a copy of an Ailment that is currently affecting a LivingEntity
public class AilmentInstance {

    //helpful to know
    private final String ailmentId;
    private int stacks;
    private int remainingDurationInTicks;

    public AilmentInstance(String ailmentId) {
        this.ailmentId = ailmentId;
    }

    public int getRemainingDurationInTicks() {
        return this.remainingDurationInTicks;
    }

    public void setRemainingDurationInTicks(int durationInTicks) {
        this.remainingDurationInTicks = durationInTicks;
    }

    public void tickDuration() {
        this.remainingDurationInTicks--;
    }

    public void onExpire() {
        ElementalsAPI.getAilment(ailmentId).onExpire();
    }

}
