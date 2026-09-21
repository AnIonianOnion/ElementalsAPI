package com.anionianonion.elementals_api.data_classes;

import java.util.HashSet;
import java.util.Set;

public class ElementCategory {

    private final String name;
    //global default vv
    private Set<Element> elements = new HashSet<>();

    public ElementCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Set<Element> getElements() {
        return this.elements;
    }

    public void setElements(Set<Element> elements) {
        this.elements = elements;
    }

    public void addElement(Element element) {
        this.elements.add(element);
    }
}
