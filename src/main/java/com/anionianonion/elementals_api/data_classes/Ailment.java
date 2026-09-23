package com.anionianonion.elementals_api.data_classes;


//Rabbit and Steel style ailments

import com.anionianonion.elementals_api.ElementalsAPIMod;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.BiConsumer;

//PoE style ailments
//ignite -> 90% of base fire damage per second for 4 seconds
//poison -> 20% of base phys + base chaos damage per second for 2 seconds
public class Ailment {

    private final String name;
    //global default vv
    private Element elementItComesFrom;
    private int durationInSeconds;
    private boolean isDamagingAilment;
    private boolean canBeInflictedFromCrit;
    private int maxStacksOnEntity;
    private boolean guaranteeInflictChance;
    private float ratioOfDPStoHitDamage = 1;

    private BiConsumer<LivingEntity, AilmentInstance> defenderOnApply = (defender, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> defenderOnTick = (defender, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> defenderOnExpire = (defender, ailmentInstance) -> {};

    private BiConsumer<LivingEntity, AilmentInstance> attackerOnApply = (attacker, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> attackerOnTick = (attacker, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> attackerOnExpire = (attacker, ailmentInstance) -> {};

    //there's too many field variables to set them in a constructor. It's probably better to make this the only constructor.
    public Ailment(String name) {
        this.name = name;
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
    public boolean canBeInflictedFromCrit() {
        return this.canBeInflictedFromCrit;
    }
    public void setCanBeInflictedFromCrit(boolean canBeInflictedFromCrit) {
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
    }


    public int getDurationInSeconds() {
        return this.durationInSeconds;
    }
    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }
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

    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnApply() {
        return this.attackerOnApply;
    }
    public void setAttackerOnApply(BiConsumer<LivingEntity, AilmentInstance> attackerOnApply) {
        this.attackerOnApply = attackerOnApply;
    }
    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnTick() {
        return this.attackerOnTick;
    }
    public void setAttackerOnTick(BiConsumer<LivingEntity, AilmentInstance> attackerOnTick) {
        this.attackerOnTick = attackerOnTick;
    }
    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnExpire() {
        return attackerOnExpire;
    }
    public void setAttackerOnExpire(BiConsumer<LivingEntity, AilmentInstance> attackerOnExpire) {
        this.attackerOnExpire = attackerOnExpire;
    }

    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnApply() {
        return this.defenderOnApply;
    }
    public void setDefenderOnApply(BiConsumer<LivingEntity, AilmentInstance> defenderOnApply) {
        this.defenderOnApply = defenderOnApply;
    }
    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnTick() {
        return this.defenderOnTick;
    }
    public void setDefenderOnTick(BiConsumer<LivingEntity, AilmentInstance> defenderOnTick) {
        this.defenderOnTick = defenderOnTick;
    }
    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnExpire() {
        return this.defenderOnExpire;
    }
    public void setDefenderOnExpire(BiConsumer<LivingEntity, AilmentInstance> defenderOnExpire) {
        this.defenderOnExpire = defenderOnExpire;
    }


    public float getRatioOfDPStoHitDamage() {
        return this.ratioOfDPStoHitDamage;
    }
    public void setRatioOfDPStoHitDamage(float ratioOfDPStoHitDamage) {
        this.ratioOfDPStoHitDamage = ratioOfDPStoHitDamage;
    }
}

