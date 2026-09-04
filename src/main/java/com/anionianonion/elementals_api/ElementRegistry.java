package com.anionianonion.elementals_api;

import java.util.HashMap;

public class ElementRegistry {

    private static final HashMap<String, Element> elementRegistry = new HashMap<>();

    public static void regElement(String elementName) {
        elementRegistry.put(elementName, new Element(elementName));
    }

    public static HashMap<String, Element> get() {
        return elementRegistry;
    }



}
