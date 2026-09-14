package com.anionianonion.elementals_api.data_classes;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.BiFunction;

//a copy of an Ailment that is currently affecting a LivingEntity
public class AilmentInstance {

    private LivingEntity affected;
    private int stacks;
    private int remainingDurationInTicks;

    private BiFunction<LivingEntity, AilmentInstance, Object> onExpire;

    public AilmentInstance(LivingEntity target, float durationInSeconds, int damage) {
        this.affected = target;
    }

    public AilmentInstance(LivingEntity target, String ailmentIdToCopyFrom, int sourceDamage) {
        this.affected = target;
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

    public void setOnExpire(BiFunction<LivingEntity, AilmentInstance, Object> onExpire) {

    }

    public void onExpire() {
        onExpire.apply(affected, this);
    }


    public void setOnTick(BiFunction<LivingEntity, AilmentInstance, Object> onTickFunction) {

    }
}
