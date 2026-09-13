package com.anionianonion.elementals_api.registries;

import com.anionianonion.elementals_api.data_classes.ElementCategory;

import java.util.HashMap;

public class ElementCategoryRegistry {

    private static final HashMap<String, ElementCategory> elementCategoryRegistry = new HashMap<>();

    public static void regAilmentCategory(ElementCategory elementCategory) {
        elementCategoryRegistry.put(elementCategory.name(), elementCategory);
    }

    public static HashMap<String, ElementCategory> get() {
        return elementCategoryRegistry;
    }
}
