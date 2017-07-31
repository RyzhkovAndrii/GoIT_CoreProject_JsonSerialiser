package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;

import java.util.Collection;

public class CollectionMapper extends AbstractMapper {

    @Override
    public void write(Object obj, IJsonWriter jsonWriter) {
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
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

}

