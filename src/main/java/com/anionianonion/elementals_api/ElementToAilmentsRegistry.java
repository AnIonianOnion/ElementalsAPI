package com.anionianonion.elementals_api;

import com.google.common.collect.HashMultimap;

import java.util.Set;

public class ElementToAilmentsRegistry {

    //using HashMultimap instead of ArraylistMultimap in order to prevent having a key with duplicate values.
    private static final HashMultimap<String, String> elementsToAilmentsRegistry = HashMultimap.create();

    public static HashMultimap<String, String> get() {
        return elementsToAilmentsRegistry;
    }

    public static void pairAilmentToElement(String ailment, String element) {
        elementsToAilmentsRegistry.put(element, ailment);
    }

    public static void setAilmentsForElement(Set<String> ailments, String element) {
        if(elementsToAilmentsRegistry.containsKey(element)) {
            elementsToAilmentsRegistry.get(element).clear();
            elementsToAilmentsRegistry.putAll(element, ailments);
        }
    }
}
