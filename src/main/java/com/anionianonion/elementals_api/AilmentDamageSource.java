package com.anionianonion.elementals_api;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


public class AilmentDamageSource extends DamageSource {

    public AilmentDamageSource(Holder<DamageType> p_270475_) {
        super(p_270475_);
    }

    public AilmentDamageSource(Holder<DamageType> p_270811_, @Nullable Entity p_270660_) {
        super(p_270811_, p_270660_);
    }

    public AilmentDamageSource(Holder<DamageType> p_270690_, Vec3 p_270579_) {
        super(p_270690_, p_270579_);
    }

    public AilmentDamageSource(Holder<DamageType> p_270818_, @Nullable Entity p_270162_, @Nullable Entity p_270115_) {
        super(p_270818_, p_270162_, p_270115_);
    }

    public AilmentDamageSource(Holder<DamageType> p_270906_, @Nullable Entity p_270796_, @Nullable Entity p_270459_, @Nullable Vec3 p_270623_) {
        super(p_270906_, p_270796_, p_270459_, p_270623_);
    }

    /*
     * from Iron's Spellbooks <a href="io.redspace.ironsspellbooks.damage.SpellDamageSource.class">SpellDamageSource</a> class, mc ver 1.20.1, iss ver 3.16.3.
     * TODO: need a better way to get the registry access without going through the level each time
     */

    public static Holder<DamageType> getHolderFromResource(Entity entity, ResourceKey<DamageType> damageTypeResourceKey) {
        var option = entity.level().registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolder(damageTypeResourceKey);
        if (option.isPresent()) {
            return option.get();
        } else {
            return entity.level().damageSources().genericKill().typeHolder();
        }
    }
}
