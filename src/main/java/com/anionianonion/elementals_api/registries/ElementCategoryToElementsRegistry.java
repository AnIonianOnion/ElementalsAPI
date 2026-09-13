package com.anionianonion.elementals_api.registries;

import com.google.common.collect.HashMultimap;

import java.util.Set;

public class ElementCategoryToElementsRegistry {

    private static final HashMultimap<String, String> elementCategoryToElementsRegistry = HashMultimap.create();

    public static void pairElementToElementCategory(String elementName, String elementCategoryName) {
        elementCategoryToElementsRegistry.put(elementCategoryName, elementName);
    }

    public static void setElementsForElementCategory(Set<String> elementNames, String elementCategoryName) {
        if(elementCategoryToElementsRegistry.containsKey(elementCategoryName)) {
            elementCategoryToElementsRegistry.get(elementCategoryName).clear();
            elementCategoryToElementsRegistry.putAll(elementCategoryName, elementNames);
        }
    }

    public static HashMultimap<String, String> get() {
        return elementCategoryToElementsRegistry;
    }

    public Set<String> get(String elementName) {
        return elementCategoryToElementsRegistry.get(elementName);
    }
}
