package com.anionianonion.elementals_api.containers;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//central system used by attacker
public class AilmentModifiersContainer {

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



    }
}
