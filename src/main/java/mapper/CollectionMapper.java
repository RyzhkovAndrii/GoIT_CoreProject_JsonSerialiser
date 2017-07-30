package main.java.mapper;

import main.java.JsonSerializer;
import main.java.writer.JsonWriter;

import java.util.Collection;

/**
 * Created by hot shihov on 11.07.2017.
 */

public class CollectionMapper extends IJsonMapper {

    @Override
    public void write(Object obj, JsonWriter jsonWriter) {
        Collection collection = (Collection) obj;
        jsonWriter.writeArrayBegin();
        if (!collection.isEmpty()) {
            JsonSerializer jsonSerializer = new JsonSerializer();
            collection.forEach((item) -> {
                jsonSerializer.serialize(item, jsonWriter);
                jsonWriter.writeSeparator();
            });
        }
        jsonWriter.writeArrayEnd();
    }
}

