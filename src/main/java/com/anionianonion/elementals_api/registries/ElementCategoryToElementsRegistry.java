package com.anionianonion.elementals_api.registries;

import com.anionianonion.elementals_api.data_classes.Element;
import com.anionianonion.elementals_api.data_classes.ElementCategory;
import com.google.common.collect.HashMultimap;

import java.util.HashSet;
import java.util.Set;

public class ElementCategoryToElementsRegistry {

    private static final HashMultimap<String, String> elementCategoryToElementsRegistry = HashMultimap.create();

    public static void pairElementToElementCategory(String elementName, String elementCategoryName) {
        //forgot to add checker for elementName and elementCategoryName
        boolean validKeys = ElementCategoryRegistry.containsKey(elementCategoryName) && ElementRegistry.containsKey(elementName);
        if(!validKeys) return;

        elementCategoryToElementsRegistry.put(elementCategoryName, elementName);

        Element element = ElementRegistry.get(elementName);
        ElementCategory elementCategory = ElementCategoryRegistry.get(elementCategoryName);

        element.setElementCategory(elementCategory);
        elementCategory.addElement(element);

    }

    public static void setElementsForElementCategory(Set<String> elementNames, String elementCategoryName) {
        //must verify each elementName as well instead of adding all.
        //it's better if we don't use prior registration to in this class's multimap to check, and instead use other registry classes, as this multimap depends on tho existence of keys
        //in those classes' hash-multimaps/hashmaps.

        //I believe because this is a hashmultimap, we can assume each key with only have unique values in the key's value set
        if(!ElementCategoryRegistry.containsKey(elementCategoryName)) return;
        for(var elementName : elementNames) {
            if(!ElementRegistry.containsKey(elementName)) return;
        }

        elementCategoryToElementsRegistry.get(elementCategoryName).clear();
        elementCategoryToElementsRegistry.putAll(elementCategoryName, elementNames);

        ElementCategory elementCategory = ElementCategoryRegistry.get(elementCategoryName);
        Set<Element> elements = new HashSet<>();
        for(var elementName : elementNames) {
            Element element = ElementRegistry.get(elementName);
            element.setElementCategory(elementCategory);
            elements.add(element);
        }
        elementCategory.setElements(elements);
    }

    public static HashMultimap<String, String> get() {
        return elementCategoryToElementsRegistry;
    }

    public Set<String> get(String elementCategoryName) {
        return elementCategoryToElementsRegistry.get(elementCategoryName);
    }
}
