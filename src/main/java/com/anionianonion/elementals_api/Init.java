package com.anionianonion.elementals_api;

import com.anionianonion.elementals_api.api.ElementalsAPI;
import com.anionianonion.elementals_api.data_classes.Ailment;
import com.anionianonion.elementals_api.data_classes.ElementCategory;
import com.anionianonion.elementals_api.registries.ElementCategoryRegistry;
import com.anionianonion.elementals_api.registries.ElementCategoryToElementsRegistry;

import java.util.HashSet;
import java.util.Set;

@Deprecated
public class Init {

    public static void initAilments() {
        ElementalsAPI.regElement("fire");
        ElementalsAPI.regElement("cold");
        ElementalsAPI.regElement("lightning");

        //todo: implement api handling instead of directly from class
        ElementCategoryRegistry.regAilmentCategory(new ElementCategory("elemental"));
        var elementalIds = new HashSet<String>();
        elementalIds.add("fire");
        elementalIds.add("ice");
        elementalIds.add("lightning");

        ElementCategoryToElementsRegistry.setElementsForElementCategory(elementalIds, "elemental");

        var ghostFlame = new Ailment("ghostflame", true, false, 1, true);
        ElementalsAPI.regAilment(ghostFlame);

        ElementalsAPI.regAilment("burning");
        var burning = ElementalsAPI.getAilment("burning");
        burning.setDamagingAilment(true);
        burning.setCanBeInflictedFromCrit(true);
        burning.setMaxStacksOnEntity(1);
    }
}
