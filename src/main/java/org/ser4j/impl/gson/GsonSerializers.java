package org.ser4j.impl.gson;

import org.ser4j.core.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * Exporting endpoint for gson serializer
 */
public class GsonSerializers {

    public static Map<String, Serializer> export() {
        Map<String, Serializer> exportable = new HashMap<>();
        exportable.put("gson/json", new GsonSerializer());
        return exportable;
    }

}
