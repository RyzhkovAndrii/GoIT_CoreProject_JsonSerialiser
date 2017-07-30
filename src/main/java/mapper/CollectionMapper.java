package main.java.mapper;

import main.java.writer.IJsonWriter;

import java.util.Collection;

public class CollectionMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter jsonWriter) {
        Collection collection = (Collection) obj;
        jsonWriter.writeArrayBegin();
        if (!collection.isEmpty()) {
            collection.forEach((item) -> {
                jsonSerializer.serialize(item, jsonWriter);
                jsonWriter.writeSeparator();
            });
        }
        jsonWriter.writeArrayEnd();
    }

}

