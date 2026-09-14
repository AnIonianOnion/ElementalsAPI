package com.anionianonion.elementals_api.capability;

import com.anionianonion.elementals_api.ElementalsAPIMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ElementalsAPIMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilityEvents {

    @SubscribeEvent
    public static void onAttachLivingEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();

        if (entity instanceof LivingEntity) {
            event.addCapability(ResourceLocation.fromNamespaceAndPath(ElementalsAPIMod.MOD_ID, "ailment_data_container"), new AilmentDataContainerProvider());
            event.addCapability(ResourceLocation.fromNamespaceAndPath(ElementalsAPIMod.MOD_ID, "ailment_modifiers_container"), new AilmentModifiersContainerProvider());
        }
    }
}
