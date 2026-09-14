package com.anionianonion.elementals_api.containers;

import com.anionianonion.elementals_api.data_classes.AilmentInstance;

import java.util.HashMap;

//used by defender
public class AilmentDataContainer {

    //need a way to know what ailments currently exist on the living entity
    private final HashMap<String, AilmentInstance> ailmentsAffectedBy = new HashMap<>();

    public void tick() {

        for(var entry : ailmentsAffectedBy.entrySet()) {
            var instance = entry.getValue();
            instance.tickDuration();

            //if duration = 0, call expire function
            //Ghostflame from Rabbit & Steel deals an arbitrary damage value when it expires
            if(instance.getRemainingDurationInTicks() <= 0) {
                instance.onExpire();
            }
        }
    }

    public void addAilment(String ailmentId, AilmentInstance ailmentInstance) {
        ailmentsAffectedBy.put(ailmentId, ailmentInstance);
    }

    public boolean containsAilment(String ailmentId) {
        return ailmentsAffectedBy.containsKey(ailmentId);
    }

}
