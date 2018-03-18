package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

import java.util.Collection;

public class CollectionMapper extends AbstractMapper {

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

