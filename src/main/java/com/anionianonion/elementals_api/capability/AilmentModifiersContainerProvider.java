package com.anionianonion.elementals_api.capability;

import com.anionianonion.elementals_api.containers.AilmentModifiersContainer;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AilmentModifiersContainerProvider implements ICapabilityProvider {

    private final AilmentModifiersContainer backend = AilmentModifiersContainer.getDefault();

    private final LazyOptional<AilmentModifiersContainer> optional =
            LazyOptional.of(() -> backend);


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        return cap == AilmentModifiersContainerCapability.INSTANCE ? optional.cast() : LazyOptional.empty();
    }
}
