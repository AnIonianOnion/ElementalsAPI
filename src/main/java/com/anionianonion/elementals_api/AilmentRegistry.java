package com.anionianonion.elementals_api;

import java.util.HashMap;

public class AilmentRegistry {

    public static final HashMap<String, Ailment> ailmentRegistry = new HashMap<>();

    public static HashMap<String, Ailment> get() {
        return ailmentRegistry;
    }

    public static void regAilment(String name, Ailment.AilmentType ailmentType) {
        if(!ailmentRegistry.containsKey(name)) ailmentRegistry.put(name, new Ailment(name, ailmentType));
    }
}
