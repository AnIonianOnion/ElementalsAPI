package com.anionianonion.elementals_api.capability;

import com.anionianonion.elementals_api.containers.AilmentDataContainer;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AilmentDataContainerProvider implements ICapabilityProvider {

    private final AilmentDataContainer backend = new AilmentDataContainer();

    private final LazyOptional<AilmentDataContainer> optional =
            LazyOptional.of(() -> backend);


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == AilmentDataContainerCapability.INSTANCE ? optional.cast() : LazyOptional.empty();
    }
}
