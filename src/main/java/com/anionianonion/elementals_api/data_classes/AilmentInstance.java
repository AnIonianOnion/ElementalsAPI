package com.anionianonion.elementals_api.data_classes;

import com.anionianonion.advanced_arpg_attributes_api.api.AdvancedARPGAttributesAPI;
import com.anionianonion.elementals_api.api.ElementalsAPI;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

//a copy of an Ailment that is currently affecting a LivingEntity
public class AilmentInstance {

    public final LivingEntity attacker;
    //this ailment instance is stored on the entity's AilmentDataContainer capability, but it's still needed in order to call onExpire & onTick functions
    public final LivingEntity defender;
    public int sourceDamage;

    private Consumer<AilmentInstance> attackerOnApply = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> attackerOnTick = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> attackerOnExpire = (ailmentInstance) -> {};

    private Consumer<AilmentInstance> defenderOnApply = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> defenderOnTick = (ailmentInstance) -> {};
    private Consumer<AilmentInstance> defenderOnExpire = (ailmentInstance) -> {};

    private Function<AilmentInstance, Float> effectStrengthFunction = ailmentInstance -> 0f;

    private int stacksCount, maxStacksCount, absoluteMaxStacksCount;
    private int remainingDurationInTicks;

    private float ratioOfDPStoHitDamage;

    private String ailmentSourceName;
    private String elementIdOfSourceAilment;

    //used for ailments such as Ghostflame, where there isn't a default behavior for the ailment.
    public AilmentInstance(LivingEntity attacker, LivingEntity target, int durationInSeconds, int baseDamagePerStack, int maxStacksCount, int absoluteMaxStacksCount) {
        this.attacker = attacker;
        this.defender = target;
        this.remainingDurationInTicks = durationInSeconds * 20;
        this.sourceDamage = baseDamagePerStack;
        this.stacksCount = 1;

        if(absoluteMaxStacksCount <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacksCount;

        if(maxStacksCount <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacksCount, this.absoluteMaxStacksCount);
    }

    //used when there is a default behavior for an ailment, which is why we use the ailmentId, in order to look up the ailment.
    public AilmentInstance(LivingEntity attacker, LivingEntity target, String ailmentIdToCopyFrom, int sourceDamage) {
        this.attacker = attacker;
        this.defender = target;
        this.sourceDamage = sourceDamage;
        this.stacksCount = 1;

        Ailment source = ElementalsAPI.getAilment(ailmentIdToCopyFrom);
        if(source == null) return;

        this.ailmentSourceName = source.getId();
        this.elementIdOfSourceAilment = source.getElementItComesFrom() != null ? source.getElementItComesFrom().getId() : null;
        this.remainingDurationInTicks = source.getDurationInSeconds() * 20;

        this.ratioOfDPStoHitDamage = source.getRatioOfDPStoHitDamage();

        this.attackerOnApply = source.getAttackerOnApply();
        this.attackerOnTick = source.getAttackerOnTick();
        this.attackerOnExpire = source.getAttackerOnExpire();
        this.defenderOnApply = source.getDefenderOnApply();
        this.defenderOnTick = source.getDefenderOnTick();
        this.defenderOnExpire = source.getDefenderOnExpire();

        this.effectStrengthFunction = source.getEffectStrengthFunction();
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
    public void setMaxStacksCount(int maxStacksCount) {
        if(maxStacksCount <= 0) this.maxStacksCount = 1;
        else this.maxStacksCount = Math.min(maxStacksCount, this.absoluteMaxStacksCount);
    }
    public int getAbsoluteMaxStacksCount() {
        return this.absoluteMaxStacksCount;
    }
    public void setAbsoluteMaxStacksCount(int absoluteMaxStacksCount) {
        if(absoluteMaxStacksCount <= 0) this.absoluteMaxStacksCount = 1;
        else this.absoluteMaxStacksCount = absoluteMaxStacksCount;
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
    public float getRatioOfDPStoHitDamage() {
        return this.ratioOfDPStoHitDamage;
    }
    public void setRatioOfDPStoHitDamage(float multiplier) {
        this.ratioOfDPStoHitDamage = multiplier;
    }


    /// Higher Order Functions

    /// Attacker
    public Consumer<AilmentInstance> getAttackerOnApply() {
        return this.attackerOnApply;
    }
    public void setAttackerOnApply(Consumer<AilmentInstance> attackerOnApply) {
        this.attackerOnApply = attackerOnApply;
    }
    public void attackerOnApply() {
        this.attackerOnApply.accept(this);
    }

    public Consumer<AilmentInstance> getAttackerOnTick() { return this.attackerOnTick; }
    public void setAttackerOnTick(Consumer<AilmentInstance> attackerOnTick) {
        this.attackerOnTick = attackerOnTick;
    }
    public void attackerOnTick() {
        this.attackerOnTick.accept(this);
    }

    public Consumer<AilmentInstance> getAttackerOnExpire() { return this.attackerOnExpire; }
    public void setAttackerOnExpire(Consumer<AilmentInstance> attackerOnExpire) {
        this.attackerOnExpire = attackerOnExpire;
    }
    public void attackerOnExpire() {
        this.attackerOnExpire.accept(this);
    }

    /// Defender
    public Consumer<AilmentInstance> getDefenderOnApply() {
        return this.defenderOnApply;
    }
    public void setDefenderOnApply(Consumer<AilmentInstance> defenderOnApply) {
        this.defenderOnApply = defenderOnApply;
    }
    public void defenderOnApply() {
        this.defenderOnApply.accept(this);
    }

    public Consumer<AilmentInstance> getDefenderOnTick() {
        return this.defenderOnTick;
    }
    public void setDefenderOnTick(Consumer<AilmentInstance> defenderOnTick) {
        this.defenderOnTick = defenderOnTick;
    }
    public void defenderOnTick() {
        this.defenderOnTick.accept(this);
    }

    public Consumer<AilmentInstance> getDefenderOnExpire() {
        return this.defenderOnExpire;
    }
    public void setDefenderOnExpire(Consumer<AilmentInstance> defenderOnExpire) {
        this.defenderOnExpire = defenderOnExpire;
    }
    public void defenderOnExpire() {
        this.defenderOnExpire.accept(this);
    }

    public Function<AilmentInstance, Float> getEffectStrengthFunction() {
        return this.effectStrengthFunction;
    }
    public void setEffectStrengthFunction(Function<AilmentInstance, Float> effectStrengthFunction) {
        this.effectStrengthFunction = effectStrengthFunction;
    }
    public float getEffectStrength() {
        return this.effectStrengthFunction.apply(this);
    }

    public int getFinalDPSPerStack() {
        Set<String> damageTags = new HashSet<>();
        damageTags.add("damage");
        damageTags.add("self");
        damageTags.add("damaging");
        damageTags.add("ailment");

        if(this.ailmentSourceName != null) {
            damageTags.add(this.ailmentSourceName);
        }
        if(this.elementIdOfSourceAilment != null) {
            damageTags.add(this.elementIdOfSourceAilment);
        }

        Set<ResourceLocation> attributeRLs = AdvancedARPGAttributesAPI.getFilteredAttributes(damageTags);
        var data = AdvancedARPGAttributesAPI.getData(this.attacker, attributeRLs);

        return Math.round(this.stacksCount * this.sourceDamage * (1 + data[1]) * (1 + data[2]));
    }
}
