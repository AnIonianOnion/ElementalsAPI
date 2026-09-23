package com.anionianonion.elementals_api.data_classes;

import com.anionianonion.advanced_arpg_attributes_api.api.AdvancedARPGAttributesAPI;
import com.anionianonion.elementals_api.api.ElementalsAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

//a copy of an Ailment that is currently affecting a LivingEntity
public class AilmentInstance {

    //this ailment instance is stored on the entity's AilmentDataContainer capability, but it's still needed in order to call onExpire & onTick functions
    private final LivingEntity affected;

    private BiConsumer<LivingEntity, AilmentInstance> onApply = (livingEntity, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> onTick = (livingEntity, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> onExpire = (livingEntity, ailmentInstance) -> {};

    private int stacksCount, maxStacksCount, absoluteMaxStacksCount;
    private int remainingDurationInTicks;
    private int baseDamage;
    private float dpsMultiplier;

    private String ailmentSourceName;
    private String elementIdOfSourceAilment;

    //used for ailments such as Ghostflame, where there isn't a default behavior for the ailment.
    public AilmentInstance(LivingEntity target, int durationInSeconds, int baseDamagePerStack, int maxStacksCount, int absoluteMaxStacksCount) {
        this.affected = target;
        this.remainingDurationInTicks = durationInSeconds * 20;
        this.baseDamage = baseDamagePerStack;
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
        if(source == null) return;

        this.ailmentSourceName = source.getName();
        this.elementIdOfSourceAilment = source.getElementItComesFrom().getName();
        this.remainingDurationInTicks = source.getDurationInSeconds() * 20;
        this.baseDamage = sourceBaseDamage;
        this.dpsMultiplier = source.getRatioOfDPStoHitDamage();
        //todo: not called, so source is null
        this.onApply = source.getDefenderOnApply();
        this.onTick = source.getDefenderOnTick();
        this.onExpire = source.getDefenderOnExpire();
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
    public float getDpsMultiplier() {
        return this.dpsMultiplier;
    }
    public void setDpsMultiplier(float multiplier) {
        this.dpsMultiplier = multiplier;
    }


    /// Higher Order Functions
    public BiConsumer<LivingEntity, AilmentInstance> getOnApply() {
        return this.onApply;
    }
    public void setOnApply(BiConsumer<LivingEntity, AilmentInstance> onApply) {
        this.onApply = onApply;
    }
    public void onApply() {
        this.onApply.accept(affected, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getOnTick() {
        return this.onTick;
    }
    public void setOnTick(BiConsumer<LivingEntity, AilmentInstance> onTick) {
        this.onTick = onTick;
    }
    public void onTick() {
        this.onTick.accept(affected, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getOnExpire() {
        return this.onExpire;
    }
    public void setOnExpire(BiConsumer<LivingEntity, AilmentInstance> onExpire) {
        this.onExpire = onExpire;
    }
    public void onExpire() {
        this.onExpire.accept(affected, this);
    }

    public int getFinalDPSPerStack() {
        Set<String> damageTags = new HashSet<>();
        damageTags.add("damage");
        damageTags.add("self");
        damageTags.add("dot");

        if(this.ailmentSourceName != null && this.elementIdOfSourceAilment != null) {
            damageTags.add(this.ailmentSourceName);
            damageTags.add(this.elementIdOfSourceAilment);
        }

        Set<ResourceLocation> attributeRLs = AdvancedARPGAttributesAPI.getFilteredAttributes(damageTags);
        var data = AdvancedARPGAttributesAPI.getData(this.affected, attributeRLs);

        return Math.round(this.baseDamage * (1 + data[1]) * (1 + data[2]));
    }
}
