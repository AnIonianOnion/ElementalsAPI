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

    public AilmentDamageSource(Holder<DamageType> type) {
        super(type);
    }

    /*
    from DamageSource class:

    public DamageSource(Holder<DamageType> p_270811_, @Nullable Entity p_270660_) {
      this(p_270811_, p_270660_, p_270660_);
   }

   calls 3-parameter constructor, and we know the 2nd and 3rd arguments passed in must be the direct and causing entity, but p_270660_ is the same for both.
   So either way, it must be the causing entity.
     */
    public AilmentDamageSource(Holder<DamageType> type, @Nullable Entity directEntityAndCausingEntity) {
        super(type, directEntityAndCausingEntity);
    }

    public AilmentDamageSource(Holder<DamageType> type, Vec3 damageSourcePosition) {
        super(type, damageSourcePosition);
    }

    /*
    from DamageSource class:

    public DamageSource(Holder<DamageType> p_270818_, @Nullable Entity p_270162_, @Nullable Entity p_270115_) {
      this(p_270818_, p_270162_, p_270115_, (Vec3)null);

    calls 4-parameter constructor below.
   }
     */
    public AilmentDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
        super(type, directEntity, causingEntity);
    }

    /*
    from DamageSource class:

    public DamageSource(Holder<DamageType> p_270906_, @Nullable Entity p_270796_, @Nullable Entity p_270459_, @Nullable Vec3 p_270623_) {
      this.type = p_270906_;
      this.causingEntity = p_270459_;
      this.directEntity = p_270796_;
      this.damageSourcePosition = p_270623_;
     */
    public AilmentDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
        super(type, directEntity, causingEntity, damageSourcePosition);
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
