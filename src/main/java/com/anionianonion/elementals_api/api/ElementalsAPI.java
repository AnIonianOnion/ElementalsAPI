package com.anionianonion.elementals_api.api;

import com.anionianonion.elementals_api.*;
import com.google.common.collect.HashMultimap;

import java.util.HashMap;
import java.util.Set;

public class ElementalsAPI {

    public static HashMap<String, Element> getElementsRegistry() {
        return ElementRegistry.get();
    }

    public static HashMap<String, Ailment> getAilmentsRegistry() {
        return AilmentRegistry.get();
    }

    public static HashMultimap<String, String> getElementsToAilmentsRegistry() {
        return ElementToAilmentsRegistry.get();
    }

    public static Set<String> getAllElementNames() {
        return getElementsRegistry().keySet();
    }

    public static Set<String> getAllAilmentNames() {
        return getAilmentsRegistry().keySet();
    }

    public static void regElement(String elementName) {
        ElementRegistry.regElement(elementName);
    }

    public static void regAilment(String ailmentName) {
        AilmentRegistry.regAilment(ailmentName);
    }

    public static void regAilment(String ailmentName, Ailment ailment) {
        AilmentRegistry.regAilment(ailmentName, ailment);
    }

}
