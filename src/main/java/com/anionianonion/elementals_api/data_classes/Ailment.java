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

    private BiConsumer<LivingEntity, AilmentInstance> onExpire = (livingEntity, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> onTick = (livingEntity, ailmentInstance) -> {};

    public Ailment(String name) {
        this.name = name;
    }
    public Ailment(String name, int durationInSeconds, boolean isDamagingAilment, boolean canBeInflictedFromCrit, int maxStacksOnEntity, boolean guaranteeInflictChance) {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.isDamagingAilment = isDamagingAilment;
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
        this.maxStacksOnEntity = maxStacksOnEntity;
        this.guaranteeInflictChance = guaranteeInflictChance;
    }
    public Ailment(String name, int durationInSeconds, boolean isDamagingAilment, boolean canBeInflictedFromCrit, int maxStacksOnEntity, boolean guaranteeInflictChance, BiConsumer<LivingEntity, AilmentInstance> onExpire, BiConsumer<LivingEntity, AilmentInstance> onTick) {
        this.name = name;
        this.durationInSeconds = durationInSeconds;
        this.isDamagingAilment = isDamagingAilment;
        this.canBeInflictedFromCrit = canBeInflictedFromCrit;
        this.maxStacksOnEntity = maxStacksOnEntity;
        this.guaranteeInflictChance = guaranteeInflictChance;
        this.onExpire = onExpire;
        this.onTick = onTick;
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
    public BiConsumer<LivingEntity, AilmentInstance> getOnTick() {
        ElementalsAPIMod.LOGGER.info(name + " Ailment#getOnTick called");
        return onTick;
    }
    public void setOnTick(BiConsumer<LivingEntity, AilmentInstance> onTick) {
        ElementalsAPIMod.LOGGER.info(name + "Ailment#setOnTick called");
        this.onTick = onTick;
    }
    public BiConsumer<LivingEntity, AilmentInstance> getOnExpire() {
        ElementalsAPIMod.LOGGER.info(name + "Ailment#getOnExpire called");
        return this.onExpire;
    }
    public void setOnExpire(BiConsumer<LivingEntity, AilmentInstance> onExpire) {
        ElementalsAPIMod.LOGGER.info(name + "Ailment#setOnExpire called");
        this.onExpire = onExpire;
    }




    public float getRatioOfDPStoHitDamage() {
        return this.ratioOfDPStoHitDamage;
    }

    public void setRatioOfDPStoHitDamage(float ratioOfDPStoHitDamage) {
        this.ratioOfDPStoHitDamage = ratioOfDPStoHitDamage;
    }
}

