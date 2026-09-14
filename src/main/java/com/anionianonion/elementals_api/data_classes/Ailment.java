package com.anionianonion.elementals_api.data_classes;


//Rabbit and Steel style ailments

//PoE style ailments
//ignite -> 90% of base fire damage per second for 4 seconds
//poison -> 20% of base phys + base chaos damage per second for 2 seconds
public class Ailment {

    private final String name;
    private Element elementItComesFrom;
    private boolean isDamagingAilment;
    private boolean canBeInflictedFromCrit;
    private int maxStacksOnEntity;
    private boolean guaranteeInflictChance;

    public Ailment(String name) {
        this.name = name;
    }

    public Ailment(String name, boolean isDamagingAilment, boolean canBeInflictedFromCrit, int maxStacksOnEntity, boolean guaranteeInflictChance) {
        this.name = name;
        this.isDamagingAilment = isDamagingAilment;
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
        this.maxStacksOnEntity = maxStacksOnEntity;
        this.guaranteeInflictChance = guaranteeInflictChance;
    }

    public String getName() {
        return this.name;
    }

    public Element getElementItComesFrom() {
        return this.elementItComesFrom;
    }

    public void setElementItComesFrom(Element element) {
        this.elementItComesFrom = element;
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

    /*
    public float getDurationInSeconds() {
        return this.durationInSeconds;
    }

    public void setDurationInSeconds(float durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
     */

    public int getMaxStacksOnEntity() {
        return this.maxStacksOnEntity;
    }

    public void setMaxStacksOnEntity(int maxStacksOnEntity) {
        this.maxStacksOnEntity = maxStacksOnEntity;
    }

    public boolean isGuaranteeInflictChance() {
        return this.guaranteeInflictChance;
    }

    public void setGuaranteeInflictChance(boolean guaranteeInflictChance) {
        this.guaranteeInflictChance = guaranteeInflictChance;
    }

    public void onExpire() {

    }
}

