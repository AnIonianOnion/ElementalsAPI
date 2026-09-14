package com.anionianonion.elementals_api.registries;

import com.anionianonion.elementals_api.data_classes.Ailment;

import java.util.HashMap;

public class AilmentRegistry {

    public static final HashMap<String, Ailment> ailmentRegistry = new HashMap<>();

    public static void regAilment(String ailmentName) {
        if(!ailmentRegistry.containsKey(ailmentName)) ailmentRegistry.put(ailmentName, new Ailment(ailmentName));
    }

    public static void regAilment(String ailmentName, Ailment ailment) {
        if(!ailmentRegistry.containsKey(ailmentName)) ailmentRegistry.put(ailmentName, ailment);
    }

    //helps to directly access the Ailment from regAilment
    public static Ailment regAilmentAndGet(String ailmentName) {
        regAilment(ailmentName);
        return get(ailmentName);
    }

    //there is no regAilmentAndGet here for second overload, unlike the one above that's for the first overload, because Ailment is what we want, and it's already used as a parameter

    public static HashMap<String, Ailment> get() {
        return ailmentRegistry;
    }

    public static Ailment get(String ailmentName) {
        return ailmentRegistry.get(ailmentName);
    }

    public static boolean containsKey(String ailmentName) {
        return ailmentRegistry.containsKey(ailmentName);
    }
}
