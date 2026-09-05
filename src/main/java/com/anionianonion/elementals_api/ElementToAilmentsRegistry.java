package com.anionianonion.elementals_api;

import com.google.common.collect.HashMultimap;

import java.util.Set;

public class ElementToAilmentsRegistry {

    //using HashMultimap instead of ArraylistMultimap in order to prevent having a key with duplicate values.
    private static final HashMultimap<String, String> elementsToAilmentsRegistry = HashMultimap.create();

    public static void pairAilmentToElement(String ailmentName, String elementName) {
        elementsToAilmentsRegistry.put(elementName, ailmentName);
    }

    public static void setAilmentsForElement(Set<String> ailments, String elementName) {
        if(elementsToAilmentsRegistry.containsKey(elementName)) {
            elementsToAilmentsRegistry.get(elementName).clear();
            elementsToAilmentsRegistry.putAll(elementName, ailments);
        }
    }

    public static HashMultimap<String, String> get() {
        return elementsToAilmentsRegistry;
    }

    public Set<String> get(String elementName) {
        return elementsToAilmentsRegistry.get(elementName);
    }
}
