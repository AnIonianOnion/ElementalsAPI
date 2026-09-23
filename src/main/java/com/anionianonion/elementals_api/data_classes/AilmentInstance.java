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

    private final LivingEntity attacker;

    //this ailment instance is stored on the entity's AilmentDataContainer capability, but it's still needed in order to call onExpire & onTick functions
    private final LivingEntity defender;

    private BiConsumer<LivingEntity, AilmentInstance> attackerOnApply = (attacker, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> attackerOnTick = (attacker, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> attackerOnExpire = (attacker, ailmentInstance) -> {};

    private BiConsumer<LivingEntity, AilmentInstance> defenderOnApply = (defender, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> defenderOnTick = (defender, ailmentInstance) -> {};
    private BiConsumer<LivingEntity, AilmentInstance> defenderOnExpire = (defender, ailmentInstance) -> {};

    private int stacksCount, maxStacksCount, absoluteMaxStacksCount;
    private int remainingDurationInTicks;
    private int baseDamage;
    private float dpsMultiplier;

    private String ailmentSourceName;
    private String elementIdOfSourceAilment;

    //used for ailments such as Ghostflame, where there isn't a default behavior for the ailment.
    public AilmentInstance(LivingEntity attacker, LivingEntity target, int durationInSeconds, int baseDamagePerStack, int maxStacksCount, int absoluteMaxStacksCount) {
        this.attacker = attacker;
        this.defender = target;
        this.remainingDurationInTicks = durationInSeconds * 20;
        this.baseDamage = baseDamagePerStack;
        this.stacksCount = 1;

        if(absoluteMaxStacksCount <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacksCount;

        if(maxStacksCount <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacksCount, this.absoluteMaxStacksCount);
    }

    //used when there is a default behavior for an ailment, which is why we use the ailmentId, in order to look up the ailment.
    public AilmentInstance(LivingEntity attacker, LivingEntity target, String ailmentIdToCopyFrom, int sourceBaseDamage) {
        this.attacker = attacker;
        this.defender = target;

        Ailment source = ElementalsAPI.getAilment(ailmentIdToCopyFrom);
        if(source == null) return;

        this.ailmentSourceName = source.getName();
        this.elementIdOfSourceAilment = source.getElementItComesFrom().getName();
        this.remainingDurationInTicks = source.getDurationInSeconds() * 20;
        this.baseDamage = sourceBaseDamage;
        this.dpsMultiplier = source.getRatioOfDPStoHitDamage();

        this.attackerOnApply = source.getAttackerOnApply();
        this.attackerOnTick = source.getAttackerOnTick();
        this.attackerOnExpire = source.getAttackerOnExpire();
        this.defenderOnApply = source.getDefenderOnApply();
        this.defenderOnTick = source.getDefenderOnTick();
        this.defenderOnExpire = source.getDefenderOnExpire();
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

    /// Attacker
    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnApply() {
        return this.attackerOnApply;
    }
    public void setAttackerOnApply(BiConsumer<LivingEntity, AilmentInstance> attackerOnApply) {
        this.attackerOnApply = attackerOnApply;
    }
    public void attackerOnApply() {
        this.attackerOnApply.accept(attacker, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnTick() { return this.attackerOnTick; }
    public void setAttackerOnTick(BiConsumer<LivingEntity, AilmentInstance> attackerOnTick) {
        this.attackerOnTick = attackerOnTick;
    }
    public void attackerOnTick() {
        this.attackerOnTick.accept(attacker, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getAttackerOnExpire() { return this.attackerOnExpire; }
    public void setAttackerOnExpire(BiConsumer<LivingEntity, AilmentInstance> attackerOnExpire) {
        this.attackerOnExpire = attackerOnExpire;
    }
    public void attackerOnExpire() {
        this.attackerOnExpire.accept(attacker, this);
    }

    /// Defender
    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnApply() {
        return this.defenderOnApply;
    }
    public void setDefenderOnApply(BiConsumer<LivingEntity, AilmentInstance> defenderOnApply) {
        this.defenderOnApply = defenderOnApply;
    }
    public void defenderOnApply() {
        this.defenderOnApply.accept(defender, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnTick() {
        return this.defenderOnTick;
    }
    public void setDefenderOnTick(BiConsumer<LivingEntity, AilmentInstance> defenderOnTick) {
        this.defenderOnTick = defenderOnTick;
    }
    public void defenderOnTick() {
        this.defenderOnTick.accept(defender, this);
    }

    public BiConsumer<LivingEntity, AilmentInstance> getDefenderOnExpire() {
        return this.defenderOnExpire;
    }
    public void setDefenderOnExpire(BiConsumer<LivingEntity, AilmentInstance> defenderOnExpire) {
        this.defenderOnExpire = defenderOnExpire;
    }
    public void defenderOnExpire() {
        this.defenderOnExpire.accept(defender, this);
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
        var data = AdvancedARPGAttributesAPI.getData(this.defender, attributeRLs);

        return Math.round(this.baseDamage * (1 + data[1]) * (1 + data[2]));
    }
}
