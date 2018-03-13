package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

import java.util.Map;

public class MapMapper extends AbstractMapper {

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
