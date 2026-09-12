package com.anionianonion.elementals_api;

import net.minecraft.world.entity.LivingEntity;

public class AilmentApplier {

    /**
    a way to apply different versions of the same ailment. In Rabbit and Steel, Poison might deal 50dps for 5s = 250 damage, but it could also deal 30ps for 4s = 120 damage.
     */
    public static void applyAilment(String ailmentId, LivingEntity target, float durationInSeconds, int damage) {
        //...
    }

    public static void applyAilment(String ailmentId, LivingEntity target, int sourceDamage) {
        //...
    }
}
