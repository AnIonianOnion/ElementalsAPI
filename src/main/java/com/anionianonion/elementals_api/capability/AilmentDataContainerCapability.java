package com.anionianonion.elementals_api.capability;

import com.anionianonion.elementals_api.containers.AilmentDataContainer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class AilmentDataContainerCapability {

    public static final Capability<AilmentDataContainer> INSTANCE =
            CapabilityManager.get(new CapabilityToken<>() {});
}
