package com.anionianonion.elementals_api.api;

import com.anionianonion.elementals_api.Element;
import com.anionianonion.elementals_api.ElementRegistry;

import java.util.HashMap;
import java.util.Set;

public class ElementalsAPI {

    public static HashMap<String, Element> getRegistry() {
        return ElementRegistry.get();
    }

    public static Set<String> getAllElementNames() {
        return getRegistry().keySet();
    }
}
