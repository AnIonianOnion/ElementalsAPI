package com.anionianonion.elementals_api.util;

import com.anionianonion.elementals_api.api.ElementalsAPI;

public class RandomHelpers {

    public static boolean areRegistered(String elementId, String ailmentId) {
        return ElementalsAPI.containsElementId(elementId) && ElementalsAPI.containsAilmentId(ailmentId);
    }
}
