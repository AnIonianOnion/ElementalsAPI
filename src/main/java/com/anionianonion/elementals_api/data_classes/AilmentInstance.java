package com.anionianonion.elementals_api.data_classes;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.BiConsumer;

//a copy of an Ailment that is currently affecting a LivingEntity
public class AilmentInstance {

    //this ailment instance is stored on the entity's AilmentDataContainer capability, but it's still needed in order to call onExpire & onTick functions
    private final LivingEntity affected;

    private BiConsumer<LivingEntity, AilmentInstance> onExpire;
    private BiConsumer<LivingEntity, AilmentInstance> onTick;

    private int stacksCount, maxStacksCount, absoluteMaxStacksCount;
    private int remainingDurationInTicks;
    private int baseDamagePerStackCount;

    //used for ailments such as Ghostflame, where there isn't a default behavior for the ailment.
    public AilmentInstance(LivingEntity target, int durationInSeconds, int baseDamagePerStack, int maxStacksCount, int absoluteMaxStacksCount) {
        this.affected = target;
        this.remainingDurationInTicks = durationInSeconds * 20;
        this.baseDamagePerStackCount = baseDamagePerStack;
        this.stacksCount = 1;

        if(absoluteMaxStacksCount <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacksCount;

        if(maxStacksCount <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacksCount, this.absoluteMaxStacksCount);
    }

    //used when there is a default behavior for an ailment, which is why we use the ailmentId, in order to look up the ailment.
    public AilmentInstance(LivingEntity target, String ailmentIdToCopyFrom, int sourceBaseDamage) {
        this.affected = target;

        Ailment source = ElementalsAPI.getAilment(ailmentIdToCopyFrom);
        this.remainingDurationInTicks = source.getDurationInSeconds() * 20;
        this.baseDamagePerStackCount = Math.round(sourceBaseDamage * source.getDpsRatio());
        this.onTick = source.getOnTick();
        this.onExpire = source.getOnExpire();
    }

    //stacks
    public int getStacksCount() {
        return this.stacksCount;
    }
    public void setStacksCount(int stacks) {
        this.stacksCount = stacks;
    }
    public int getMaxStacksCount() {
        return this.maxStacksCount;
    }
    public void setMaxStacksCount(int maxStacks) {
        if(maxStacks <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacks, this.absoluteMaxStacksCount);
    }
    public int getAbsoluteMaxStacksCount() {
        return this.absoluteMaxStacksCount;
    }
    public void setAbsoluteMaxStacksCount(int absoluteMaxStacks) {
        if(absoluteMaxStacks <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacks;
    }
    //ticks
    public int getRemainingDurationInTicks() {
        return this.remainingDurationInTicks;
    }
    public void setRemainingDurationInTicks(int durationInTicks) {
        this.remainingDurationInTicks = Math.max(durationInTicks, 0);
    }
    public void tickDuration() {
        this.remainingDurationInTicks--;
    }

    public void setOnExpire(BiConsumer<LivingEntity, AilmentInstance> onExpire) {
        this.onExpire = onExpire;
    }
    //changed to BiConsumers from BiFunctions, because BiConsumers don't return anything.
    public void onExpire() {
        this.onExpire.accept(affected, this);
    }
    public BiConsumer<LivingEntity, AilmentInstance> getOnExpire() {
        return this.onExpire;
    }

    public void setOnTick(BiConsumer<LivingEntity, AilmentInstance> onTick) {
        this.onTick = onTick;
    }
    public void onTick() {
        this.onTick.accept(affected, this);
    }
    public BiConsumer<LivingEntity, AilmentInstance> getOnTick() {
        return this.onTick;
    }
}
