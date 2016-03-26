package org.ser4j.impl.gson;

import com.google.gson.Gson;
import org.ser4j.core.SerializedForm;
import org.ser4j.core.Serializer;
import org.ser4j.core.impl.serializedform.StringSerializedForm;

/**
 * Implementation of serializer on top of Google's Gson library
 */
public class GsonSerializer implements Serializer {

    private static Gson gson = new Gson();

    @Override
    public <T> T deserialize(SerializedForm serializedForm, Class<T> aClass) {
        return gson.fromJson(serializedForm.asString(), aClass);
    }

    @Override
    public <T> SerializedForm serialize(T t) {
        return new StringSerializedForm(gson.toJson(t));
    }
}
