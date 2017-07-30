package main.java.mapper;

import main.java.JsonSerializer;
import main.java.writer.IJsonWriter;

import java.util.Map;
import java.util.Set;

public class MapMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            Map map = (Map) obj;
            writer.writeObjectBegin();
            if (map.isEmpty()) {
                writer.writeObjectEnd();
            } else {
                Set mapSet = map.entrySet();
                Map.Entry entry = (Map.Entry) mapSet.iterator().next();
                Class keyClazz = entry.getKey().getClass();
                Class valueClazz = entry.getValue().getClass();
                IJsonMapper keyMapper = jsonSerializer.getMapper(keyClazz);
                IJsonMapper valueMapper = jsonSerializer.getMapper(valueClazz);

                for (Object entryElement : map.entrySet()) {
                    Map.Entry entryMap = (Map.Entry) entryElement;
                    keyMapper.write(entryMap.getKey(), writer);
                    writer.writePropertySeparator();
                    valueMapper.write(entryMap.getValue(), writer);
                    writer.writeSeparator();
                }

                writer.writeObjectEnd();
            }
        }
        writer.flush();
    }
}
