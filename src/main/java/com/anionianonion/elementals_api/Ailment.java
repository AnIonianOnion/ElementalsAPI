package com.anionianonion.elementals_api;


//Rabbit and Steel style ailments

//PoE style ailments
//ignite -> 90% of base fire damage per second for 4 seconds
//poison -> 20% of base phys + base chaos damage per second for 2 seconds
public class Ailment {

    private String name;
    private boolean isDamagingAilment;
    private boolean canBeInflictedFromCrit;
    private float durationInSeconds;

    public Ailment(String name) {
        this.name = name;
    }

    public Ailment(String name, boolean isDamagingAilment, boolean canBeInflictedFromCrit, float durationInSeconds) {
        this.name = name;
        this.isDamagingAilment = isDamagingAilment;
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
        this.durationInSeconds = durationInSeconds;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDamagingAilment() {
        return this.isDamagingAilment;
    }

    public void setDamagingAilment(boolean damagingAilment) {
        this.isDamagingAilment = damagingAilment;
    }

    public boolean isCanBeInflictedFromCrit() {
        return this.canBeInflictedFromCrit;
    }

    public void setCanBeInflictedFromCrit(boolean canBeInflictedFromCrit) {
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
    }

    public float getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
}

