package main.java.mapper;

import main.java.writer.IJsonWriter;

import java.util.Map;
import java.util.Set;

public class MapMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        Map map = (Map) obj;
        writer.writeArrayBegin();
        if (!map.isEmpty()) {
            Set<Map.Entry> entrySet = map.entrySet();
            entrySet.stream().forEach(item -> {
                jsonSerializer.serialize(item.getKey(), writer);
                writer.writePropertySeparator();
                jsonSerializer.serialize(item.getValue(), writer);
                writer.writeSeparator();
            });
        }
        writer.writeArrayEnd();
    }
}
