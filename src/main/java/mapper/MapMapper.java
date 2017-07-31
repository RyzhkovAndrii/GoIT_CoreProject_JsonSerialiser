package main.java.mapper;

import main.java.writer.IJsonWriter;

import java.util.Map;

public class MapMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            Map map = (Map) obj;
            writer.writeArrayBegin();
            if (!map.isEmpty()) {
                map.forEach((key, value) -> {
                    jsonSerializer.serialize(key, writer);
                    writer.writePropertySeparator();
                    jsonSerializer.serialize(value, writer);
                    writer.writeSeparator();
                });
            }
            writer.writeArrayEnd();
        }
    }

}
