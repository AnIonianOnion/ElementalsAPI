package com.anionianonion.elementals_api.containers;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.data.structures.NbtToSnbt;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//central system used by attacker
public class AilmentModifiersContainer implements INBTSerializable<CompoundTag> {

    //a container inside the LivingEntity that tells if there are any special modifiers to ailments.

    //examples from PoE:
    //Three Dragons:
        //Your Fire Damage can Shock but not Ignite
        //Your Cold Damage can Ignite but not Freeze or Chill
        //Your Lightning Damage can Freeze but not Shock

    //Shaper of Flames:
        //All Damage can Ignite
        //Hits always Ignite
        //Enemies ignited by you have 40% of damage they deal converted to Fire

    //Shaper of Winter
    //All Damage with Hits can Chill
        //Your Chills can reduce Action Speed by up to a maximum of 40%
        //Enemies Chilled by your Hits lessen their Damage dealt by half of Chill Effect

    //Shaper of Storms:
        //Hits always Shock
        //All Damage can Shock
        //Shocks from your Hits always increase Damage taken by at least 25%


    /// each String is an id for a registered element or ailment.
    //Three Dragons
    //needed to change from Multimap to Hashmultimap to avoid duplicates
    private final HashMultimap<String, String> ailmentsToInflictForWhichElement = HashMultimap.create();

    //ailments such as Ghostflame, which may not have a specific element it comes froms
    private final Set<String> specialAilmentsPlayerCanInflict = new HashSet<>();

    //need a way to know the max number of stacks for each ailment
    private final HashMap<String, Integer> extraMaxStacksForAilment = new HashMap<>();

    //keeps track of ailment replacements like if the player should inflict Scorch instead of Ignite
    private final HashMap<String, String> ailmentReplacements = new HashMap<>();


    public HashMultimap<String, String> getAilmentsToInflictForWhichElement() {
        return ailmentsToInflictForWhichElement;
    }

    public HashMap<String, Integer> getExtraMaxStacksForAilment() {
        return extraMaxStacksForAilment;
    }

    public HashMap<String, String> getAilmentReplacements() {
        return ailmentReplacements;
    }

    public Set<String> getSpecialAilmentsPlayerCanInflict() {
        return this.specialAilmentsPlayerCanInflict;
    }

    public void init() {
        ailmentsToInflictForWhichElement.clear();
        specialAilmentsPlayerCanInflict.clear();
        extraMaxStacksForAilment.clear();
        ailmentReplacements.clear();

        for(var entry : ElementalsAPI.getElementsToAilmentsRegistry().entries()) {
            var elementId = entry.getKey();
            var ailmentId = entry.getValue();
            ailmentsToInflictForWhichElement.put(elementId, ailmentId);
        }
    }

    @Override
    public CompoundTag serializeNBT() {

        var compoundTag = new CompoundTag();

            var ailmentsToInflictForWhichElementTag = new CompoundTag();
            //a multimap is a list of values bounded to a single key.
            //a hash multimap is a set of values bounded to a single key.
            for(var elementId : ailmentsToInflictForWhichElement.keySet()) {
                if(elementId == null) continue;

                ListTag ailmentTag = new ListTag();
                for(var ailmentId : ailmentsToInflictForWhichElement.get(elementId)) {
                    if(ailmentId == null) continue;

                    ailmentTag.add(StringTag.valueOf(ailmentId));
                }

                ailmentsToInflictForWhichElementTag.put(elementId, ailmentTag);
            }

        compoundTag.put("ailmentsToInflictForWhichElement", ailmentsToInflictForWhichElementTag);

            var specialAilmentsPlayerCanInflictTag = new ListTag();

            for(var ailmentId : specialAilmentsPlayerCanInflict) {
                specialAilmentsPlayerCanInflictTag.add(StringTag.valueOf(ailmentId));
            }

        compoundTag.put("specialAilmentsPlayerCanInflict", specialAilmentsPlayerCanInflictTag);

            var extraMaxStacksForAilmentTag = new CompoundTag();

            for(var entry : extraMaxStacksForAilment.entrySet()) {
                var ailmentId = entry.getKey();
                var extraMaxStacks = entry.getValue();

                extraMaxStacksForAilmentTag.putInt(ailmentId, extraMaxStacks);
            }

        compoundTag.put("extraMaxStacksForAilment", extraMaxStacksForAilmentTag);

            var ailmentReplacementsTag = new CompoundTag();

            for(var entry : ailmentReplacements.entrySet()) {
                var replacedAilmentId = entry.getKey();
                var replacementAilmentId = entry.getValue();

                ailmentReplacementsTag.putString(replacedAilmentId, replacementAilmentId);
            }

        compoundTag.put("ailmentReplacements", ailmentReplacementsTag);

        return compoundTag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        var ailmentsToInflictForWhichElementTag = (CompoundTag) nbt.get("ailmentsToInflictForWhichElement");

            if(ailmentsToInflictForWhichElementTag != null) {
                var elementIds = ailmentsToInflictForWhichElementTag.getAllKeys();
                for(var elementIdKey : elementIds) {

                    ListTag ailmentIds = (ListTag) ailmentsToInflictForWhichElementTag.get(elementIdKey);
                    if(ailmentIds == null) continue;

                    for(var ailmentIdTag : ailmentIds) {
                        var ailmentId = ailmentIdTag.getAsString();
                        ailmentsToInflictForWhichElement.put(elementIdKey, ailmentId);
                    }
                }
            }

        var specialAilmentsPlayerCanInflictTag = (ListTag) nbt.get("specialAilmentsPlayerCanInflict");

            if(specialAilmentsPlayerCanInflictTag != null) {

                for(var ailmentIdTag : specialAilmentsPlayerCanInflictTag) {
                    var ailmentId = ailmentIdTag.getAsString();
                    specialAilmentsPlayerCanInflict.add(ailmentId);
                }
            }

        var extraMaxStacksForAilmentTag = (CompoundTag) nbt.get("extraMaxStacksForAilment");

            if(extraMaxStacksForAilmentTag != null) {
                for(var ailmentIdKey : extraMaxStacksForAilmentTag.getAllKeys()) {
                    var extraMaxStacks = extraMaxStacksForAilmentTag.getInt(ailmentIdKey);
                    extraMaxStacksForAilment.put(ailmentIdKey, extraMaxStacks);
                }
            }

        var ailmentReplacementsTag = (CompoundTag) nbt.get("ailmentReplacements");

            if(ailmentReplacementsTag != null) {
                for(var ailmentIdKey : ailmentReplacementsTag.getAllKeys()) {
                    var ailmentReplacement = ailmentReplacementsTag.getString(ailmentIdKey);
                    ailmentReplacements.put(ailmentIdKey, ailmentReplacement);
                }
            }
    }

    /// static methods
    // I always do var ailmentModifiersContainer = livingEntity.getCapability(AilmentModifiersContainerCapability.INSTANCE).orElse(null);
    //but it can also be initialized with:
    // var ailmentModifiersContainer = livingEntity.getCapability(AilmentModifiersContainerCapability.INSTANCE).orElse(AilmentModifiers.getDefault());
    public static AilmentModifiersContainer getDefault() {

        var defaultAilmentModifiersContainer = new AilmentModifiersContainer();
        defaultAilmentModifiersContainer.init();

        return defaultAilmentModifiersContainer;
    }
}
