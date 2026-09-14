package com.anionianonion.elementals_api.registries;

import com.anionianonion.elementals_api.data_classes.Ailment;
import com.anionianonion.elementals_api.data_classes.Element;
import com.anionianonion.elementals_api.data_classes.ElementCategory;
import com.google.common.collect.HashMultimap;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DefaultElementToAilmentsRegistry {

    //using HashMultimap instead of ArraylistMultimap in order to prevent having a key with duplicate values.
    private static final HashMultimap<String, String> elementsToAilmentsRegistry = HashMultimap.create();

    public static void pairAilmentToElement(String ailmentName, String elementName) {

        //based on what I did in ElementCategoryToElements class - AnIonianOnion
        boolean validKeys = AilmentRegistry.containsKey(ailmentName) && ElementRegistry.containsKey(elementName);
        if(!validKeys) return;

        elementsToAilmentsRegistry.put(elementName, ailmentName);

        Element element = ElementRegistry.get(elementName);
        Ailment ailment = AilmentRegistry.get(ailmentName);

        element.addAilment(ailment);
        ailment.setElementItComesFrom(element);
    }

    public static void setAilmentsForElement(Set<String> ailmentNames, String elementName) {
        //based on what I did in ElementCategoryToElements class - AnIonianOnion
        if(!ElementRegistry.containsKey(elementName)) return;
        for(var ailmentName : ailmentNames) {
            if(!AilmentRegistry.containsKey(ailmentName)) return;
        }

        elementsToAilmentsRegistry.get(elementName).clear();
        elementsToAilmentsRegistry.putAll(elementName, ailmentNames);

        Element element = ElementRegistry.get(elementName);
        Set<Ailment> ailments = new HashSet<>();
        for(var ailmentName : ailmentNames) {
            Ailment ailment = AilmentRegistry.get(ailmentName);
            ailment.setElementItComesFrom(element);
        }
        element.setAilments(ailments);
    }

    public static HashMultimap<String, String> get() {
        return elementsToAilmentsRegistry;
    }

    public Set<String> get(String elementName) {
        return elementsToAilmentsRegistry.get(elementName);
    }
}
