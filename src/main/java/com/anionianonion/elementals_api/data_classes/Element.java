package com.anionianonion.elementals_api.data_classes;

import java.util.HashSet;
import java.util.Set;

public class Element {

    private final String name;
    private ElementCategory elementCategory;
    private Set<Ailment> ailments = new HashSet<>();

    public Element(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public ElementCategory getElementCategory() {
        return this.elementCategory;
    }

    public void setElementCategory(ElementCategory elementCategory) {
        this.elementCategory = elementCategory;
    }

    public Set<Ailment> getAilments() {
        return this.ailments;
    }

    public void setAilments(Set<Ailment> ailments) {
        this.ailments = ailments;
    }

    public void addAilment(Ailment ailment) {
        this.ailments.add(ailment);
    }
}
