package org.ser4j.impl.gson;

import com.google.gson.Gson;
import org.ser4j.core.SerializedForm;
import org.ser4j.core.Serializer;
import org.ser4j.core.impl.serializedform.StringSerializedForm;

import java.util.HashMap;
import java.util.Map;

/**
 * Exporting endpoint for gson serializer
 */
public class GsonSerializers {

    public static Map<String, Serializer> export() {
        Map<String, Serializer> exportable = new HashMap<>();
        exportable.put("gson/json", new Serializer() {
            private Gson gson = new Gson();

            @Override
            public <T> T deserialize(SerializedForm serializedForm, Class<T> aClass) {
                return gson.fromJson(serializedForm.asString(), aClass);
            }

            @Override
            public <T> SerializedForm serialize(T t) {
                return new StringSerializedForm(gson.toJson(t));
            }
        });
        return exportable;
    }

}
