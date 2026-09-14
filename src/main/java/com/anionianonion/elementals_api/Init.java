package com.anionianonion.elementals_api;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import com.anionianonion.elementals_api.data_classes.Ailment;
import com.anionianonion.elementals_api.data_classes.ElementCategory;

import java.util.HashSet;

@Deprecated
public class Init {

    public static void initAilments() {

        //todo: implement api handling instead of directly from class
        ElementalsAPI.regElement("fire");
        ElementalsAPI.regElement("ice");
        ElementalsAPI.regElement("lightning");

        ElementalsAPI.regAilmentCategory(new ElementCategory("elemental"));
        var elementalIds = new HashSet<String>();
        elementalIds.add("fire");
        elementalIds.add("ice");
        elementalIds.add("lightning");

        ElementalsAPI.setElementsForElementCategory(elementalIds, "elemental");

        var ghostFlame = new Ailment("ghostflame", true, false, 1, true);
        ElementalsAPI.regAilment(ghostFlame);

        ElementalsAPI.regAilment("burning");
        var burning = ElementalsAPI.getAilment("burning");
        burning.setDamagingAilment(true);
        burning.setCanBeInflictedFromCrit(true);
        burning.setMaxStacksOnEntity(1);

        ElementalsAPI.pairAilmentToElement("burning", "fire");
    }
}
