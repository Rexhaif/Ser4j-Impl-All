package org.ser4j.impl.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.smile.SmileFactory;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.ser4j.core.Serializer;
import org.ser4j.core.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Exporting end point
 */
public class JacksonSerializers {

    private static Map<String, Serializer> jacksonSerializers = new HashMap<>();

    static {
        addComplexSerializers("json",  new JsonFactory());
        addComplexSerializers("yaml",  new YAMLFactory());
        addComplexSerializers("cbor",  new CBORFactory());
        addComplexSerializers("smile", new SmileFactory());
        addComplexSerializers("xml",   new XmlFactory());
    }

    private static void addSerializer(String name, Serializer serializer) {
        jacksonSerializers.put(name, serializer);
    }

    private static void addComplexSerializers(String formatName, JsonFactory factory) {
        addSerializer(
                "jackson/" + formatName,
                JacksonSerializerFactory.createSerializer(
                        factory,
                        Throwable::printStackTrace
                )
        );
        addSerializer(
                "jackson/" + formatName + "/ignore_error",
                JacksonSerializerFactory.createSerializer(
                        factory,
                        Util::doNothingWithThrowable
                )
        );
    }

    public static Map<String, Serializer> export() {
        return jacksonSerializers;
    }

}
