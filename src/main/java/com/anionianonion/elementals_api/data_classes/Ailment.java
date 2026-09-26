package com.anionianonion.elementals_api.data_classes;

import java.util.function.Consumer;
import java.util.function.Function;

//Rabbit and Steel style ailments
//Paint - reapplies a random ailment once Paint expires

//PoE style ailments
//ignite -> 90% of base fire damage per second for 4 seconds
//poison -> 20% of base phys + base chaos damage per second for 2 seconds
public class Ailment {

    //final or effectively final vv
    private final String id;
    private Element elementItComesFrom;
    private boolean isDamagingAilment;
    private boolean canBeInflictedFromCrit;
    private boolean guaranteeInflictChance;
    private int absoluteMaxStacksCount;

    //global defaults vv
    private int durationInSeconds = 3;
    private float ratioOfDPStoHitDamage = 1;
    private int maxStacksCount = 1;

    //switched to just a consumer, because AilmentInstance now keeps track of the attacker and defender.
    //deciding to keep defender&attacker onApply&onTick&onExpire separate for more clarity when debugging.
    private Consumer<AilmentInstance> defenderOnApply = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> defenderOnTick = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> defenderOnExpire = (ailmentInstance) -> {};

    private Consumer<AilmentInstance> attackerOnApply = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> attackerOnTick = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> attackerOnExpire = (ailmentInstance) -> {};

    //need a function that inputs:
    // 1. a function that determines effect strength of ailment based on attacker and defender's stats
    private Function<AilmentInstance, Float> effectStrengthFunction = (ailmentInstance) -> 0f;

    //there's too many field variables to set them in a constructor. It's probably better to make this the only constructor.
    public Ailment(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
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

    public boolean canBeInflictedFromCrit() {
        return this.canBeInflictedFromCrit;
    }
    public void setCanBeInflictedFromCrit(boolean canBeInflictedFromCrit) {
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
    }

    public boolean isGuaranteedInflictChance() {
        return this.guaranteeInflictChance;
    }
    public void setGuaranteeInflictChance(boolean guaranteeInflictChance) {
        this.guaranteeInflictChance = guaranteeInflictChance;
    }

    public int getAbsoluteMaxStacksCount() {
        return this.absoluteMaxStacksCount;
    }
    public void setAbsoluteMaxStacksCount(int absoluteMaxStacksCount) {
        if(absoluteMaxStacksCount <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacksCount;
    }

    public int getDurationInSeconds() {
        return this.durationInSeconds;
    }
    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public float getRatioOfDPStoHitDamage() {
        return this.ratioOfDPStoHitDamage;
    }
    public void setRatioOfDPStoHitDamage(float ratioOfDPStoHitDamage) {
        this.ratioOfDPStoHitDamage = ratioOfDPStoHitDamage;
    }

    public int getMaxStacksCount() {
        return this.maxStacksCount;
    }
    public void setMaxStacksCount(int maxStacksCount) {
        if(maxStacksCount <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacksCount, this.absoluteMaxStacksCount);
    }

    public Consumer<AilmentInstance> getAttackerOnApply() {
        return this.attackerOnApply;
    }
    public void setAttackerOnApply(Consumer<AilmentInstance> attackerOnApply) {
        this.attackerOnApply = attackerOnApply;
    }
    public Consumer<AilmentInstance> getAttackerOnTick() {
        return this.attackerOnTick;
    }
    public void setAttackerOnTick(Consumer<AilmentInstance> attackerOnTick) {
        this.attackerOnTick = attackerOnTick;
    }
    public Consumer<AilmentInstance> getAttackerOnExpire() {
        return attackerOnExpire;
    }
    public void setAttackerOnExpire(Consumer<AilmentInstance> attackerOnExpire) {
        this.attackerOnExpire = attackerOnExpire;
    }

    public Consumer<AilmentInstance> getDefenderOnApply() {
        return this.defenderOnApply;
    }
    public void setDefenderOnApply(Consumer<AilmentInstance> defenderOnApply) {
        this.defenderOnApply = defenderOnApply;
    }
    public Consumer<AilmentInstance> getDefenderOnTick() {
        return this.defenderOnTick;
    }
    public void setDefenderOnTick(Consumer<AilmentInstance> defenderOnTick) {
        this.defenderOnTick = defenderOnTick;
    }
    public Consumer<AilmentInstance> getDefenderOnExpire() {
        return this.defenderOnExpire;
    }
    public void setDefenderOnExpire(Consumer<AilmentInstance> defenderOnExpire) {
        this.defenderOnExpire = defenderOnExpire;
    }

    public Function<AilmentInstance, Float> getEffectStrengthFunction() {
        return this.effectStrengthFunction;
    }
    public void setEffectStrengthFunction(Function<AilmentInstance, Float> effectStrengthFunction) {
        this.effectStrengthFunction = effectStrengthFunction;
    }
}

