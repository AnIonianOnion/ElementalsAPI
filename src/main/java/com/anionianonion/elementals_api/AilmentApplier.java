package com.anionianonion.elementals_api;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import com.anionianonion.elementals_api.capability.AilmentDataContainerCapability;
import com.anionianonion.elementals_api.data_classes.AilmentInstance;
import net.minecraft.world.entity.LivingEntity;

public class AilmentApplier {

    /**
    a way to apply different versions of the same ailment. In Rabbit and Steel, Poison might deal 50dps for 5s = 250 damage, but it could also deal 30ps for 4s = 120 damage.
     */
    public static void applyAilment(String ailmentId, LivingEntity attacker, LivingEntity target, int durationInSeconds, int damage, int maxStacks, int absoluteMaxStacks) {
        var ailmentDataContainer = target.getCapability(AilmentDataContainerCapability.INSTANCE).resolve().orElse(null);

        if(ailmentDataContainer == null || !ElementalsAPI.getAllAilmentNames().contains(ailmentId)) return;

        var newAilmentInstance = new AilmentInstance(attacker, target, durationInSeconds, damage, maxStacks, absoluteMaxStacks);
        if(!ailmentDataContainer.containsAilment(ailmentId)) ailmentDataContainer.addAilment(ailmentId, newAilmentInstance);
        //else if ailment instance's stack count is less than ailment's maximum stack count, add stack
            //if ailment allows refresh, refresh duration
        //else if at max stacks,
            //if ailment allows refresh, refresh duration to max
            //if ailment's damage is higher and if allow refresh, update damage higher and refresh
            //if ailment's damage is higher and if not allow refresh, update damage higher
            //if ailment's effect is stronger and if allow refresh, update effect to be stronger and refresh
            //if ailment's effect is stronger and if not allow refresh, update effect to be stronger but don't refresh

        //but someone can easily abuse the overrides, like an ability with low damage and low cooldown, will be overrided by an ability with higher damage.
        //if this is the case, must override the cooldown as well.
    }

    public static void applyAilment(String ailmentId, LivingEntity target, AilmentInstance ailmentInstance) {
        var ailmentDataContainer = target.getCapability(AilmentDataContainerCapability.INSTANCE).resolve().orElse(null);

        if(ailmentDataContainer == null || !ElementalsAPI.getAllAilmentNames().contains(ailmentId)) return;
        if(!ailmentDataContainer.containsAilment(ailmentId)) ailmentDataContainer.addAilment(ailmentId, ailmentInstance);
        //else if ailment instance's stack count is less than ailment's maximum stack count, add stack
        //if ailment allows refresh, refresh duration
        //else if at max stacks,
        //if ailment allows refresh, refresh duration to max
        //if ailment's damage is higher and if allow refresh, update damage higher and refresh
        //if ailment's damage is higher and if not allow refresh, update damage higher
        //if ailment's effect is stronger and if allow refresh, update effect to be stronger and refresh
        //if ailment's effect is stronger and if not allow refresh, update effect to be stronger but don't refresh

        //but someone can easily abuse the overrides, like an ability with low damage and low cooldown, will be overrided by an ability with higher damage.
        //if this is the case, must override the cooldown as well.
        ElementalsAPIMod.LOGGER.info("apply ailment #2 called");
    }

    public static void applyAilment(String ailmentId, LivingEntity attacker, LivingEntity target, int sourceDamage) {
        //...
        var ailmentDataContainer = target.getCapability(AilmentDataContainerCapability.INSTANCE).resolve().orElse(null);

        if(ailmentDataContainer == null || !ElementalsAPI.getAllAilmentNames().contains(ailmentId)) return;

        var newAilmentInstance = new AilmentInstance(attacker, target, ailmentId, sourceDamage);
        if(!ailmentDataContainer.containsAilment(ailmentId)) ailmentDataContainer.addAilment(ailmentId, newAilmentInstance);
        //else if ailment instance's stack count is less than ailment's maximum stack count, add stack
        //if ailment allows refresh, refresh duration
        //else if at max stacks,
        //if ailment allows refresh, refresh duration to max
        //if ailment's damage is higher and if allow refresh, update damage higher and refresh
        //if ailment's damage is higher and if not allow refresh, update damage higher
        //if ailment's effect is stronger and if allow refresh, update effect to be stronger and refresh
        //if ailment's effect is stronger and if not allow refresh, update effect to be stronger but don't refresh

        //but someone can easily abuse the overrides, like an ability with low damage and low cooldown, will be overrided by an ability with higher damage.
        //if this is the case, must override the cooldown as well.
        ElementalsAPIMod.LOGGER.info("apply ailment #3 called");
    }
}
