package org.ser4j.impl.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ser4j.core.SerializedForm;
import org.ser4j.core.Serializer;
import org.ser4j.core.impl.serializedform.StringSerializedForm;

import java.io.IOException;
import java.util.function.Consumer;

/**
 * Factory of jackson's serializers for various data formats
 */
public class JacksonSerializerFactory {

    public static Serializer createSerializer(JsonFactory formatFactory, Consumer<Throwable> exceptionCallback) {
        return new Serializer() {

            private ObjectMapper mapper = new ObjectMapper(formatFactory);

            @Override
            public <T> T deserialize(SerializedForm serializedForm, Class<T> aClass) {
                T object = null;
                try {
                    object =  mapper.readValue(serializedForm.asByteArray(), aClass);
                } catch (IOException e) {
                    exceptionCallback.accept(e);
                }
                return object;
            }

            @Override
            public <T> SerializedForm serialize(T t) {
                SerializedForm serializedForm = null;
                try {
                    serializedForm = new StringSerializedForm(mapper.writeValueAsString(t));
                } catch (JsonProcessingException e) {
                    exceptionCallback.accept(e);
                }
                return serializedForm;
            }
        };
    }

}
