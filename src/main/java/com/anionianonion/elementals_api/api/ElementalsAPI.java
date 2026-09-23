package com.anionianonion.elementals_api.api;

import com.anionianonion.elementals_api.data_classes.Ailment;
import com.anionianonion.elementals_api.data_classes.Element;
import com.anionianonion.elementals_api.data_classes.ElementCategory;
import com.anionianonion.elementals_api.registries.*;
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
        return DefaultElementToAilmentsRegistry.get();
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

    public static Ailment regAilmentAndGet(String ailmentName) {
        AilmentRegistry.regAilmentAndGet(ailmentName);
    }

    public static void regAilment(Ailment ailment) {
        AilmentRegistry.regAilment(ailment.getName(), ailment);
    }

    public static Element getElement(String elementName) {
        return ElementRegistry.get(elementName);
    }

    public static Ailment getAilment(String ailmentName) {
        return AilmentRegistry.get(ailmentName);
    }

    public static void pairAilmentToElement(String ailmentName, String elementName) {
        DefaultElementToAilmentsRegistry.pairAilmentToElement(ailmentName, elementName);
    }

    public static void setAilmentsForElement(Set<String> ailments, String elementName) {
        DefaultElementToAilmentsRegistry.setAilmentsForElement(ailments, elementName);
    }

    public static void regAilmentCategory(ElementCategory elementCategory) {
        ElementCategoryRegistry.regAilmentCategory(elementCategory);
    }

    public static void setElementsForElementCategory(Set<String> elementNames, String elementCategoryName) {
        ElementCategoryToElementsRegistry.setElementsForElementCategory(elementNames, elementCategoryName);
    }

    public static boolean containsElementId(String elementName) {
        return ElementRegistry.containsKey(elementName);
    }

    public static boolean containsAilmentId(String ailmentName) {
        return AilmentRegistry.containsKey(ailmentName);
    }

}
