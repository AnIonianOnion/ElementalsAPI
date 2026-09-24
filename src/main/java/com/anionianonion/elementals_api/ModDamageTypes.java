package com.anionianonion.elementals_api;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;

import java.util.Objects;

public class ModDamageTypes {

    //https://forums.minecraftforge.net/topic/122311-1194-how-to-create-custom-damagesources/. 7194.f.null's response.
    public static ResourceKey<DamageType> AILMENT_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, Objects.requireNonNull(ResourceLocation.parse(String.format("%s:ailment_damage", ElementalsAPIMod.MOD_ID))));

}
