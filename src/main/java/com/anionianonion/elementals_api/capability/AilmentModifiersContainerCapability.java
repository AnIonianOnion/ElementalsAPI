package com.anionianonion.elementals_api.capability;

import com.anionianonion.elementals_api.containers.AilmentModifiersContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class AilmentModifiersContainerCapability {

    public static final Capability<AilmentModifiersContainer> INSTANCE =
            CapabilityManager.get(new CapabilityToken<>() {});
}
