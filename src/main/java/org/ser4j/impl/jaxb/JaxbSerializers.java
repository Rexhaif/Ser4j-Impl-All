package org.ser4j.impl.jaxb;

import org.ser4j.core.SerializedForm;
import org.ser4j.core.Serializer;
import org.ser4j.core.impl.serializedform.StringSerializedForm;

import javax.xml.bind.JAXB;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains "Java XML Binding"-based serializer
 */
public class JaxbSerializers {

    public Map<String, Serializer> export() {
        HashMap<String, Serializer> serializer = new HashMap<>();
        serializer.put("jdk-jaxb/xml", new Serializer() {
            @Override
            public <T> T deserialize(SerializedForm serializedForm, Class<T> aClass) {
                return JAXB.unmarshal(serializedForm.asString(), aClass);
            }

            @Override
            public <T> SerializedForm serialize(T t) {
                String string = "";
                JAXB.marshal(t, string);
                return new StringSerializedForm(string);
            }
        });
        return serializer;
    }

}
