package main.java.mapper;

import main.java.JsonSerializer;
import main.java.writer.JsonWriter;

import java.util.Map;
import java.util.Set;

/**
 * Created by hot shihov on 11.07.2017.
 */

public class MapMapper extends IJsonMapper {
    private Map map;
    private JsonSerializer jsonSerializer;

    @Override
    public void write(Object obj, JsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            map = (Map) obj;
            writer.writeObjectBegin();
            if (map.isEmpty()) {
                writer.writeObjectEnd();
            } else {
                Set mapSet = map.entrySet();
                Map.Entry entry = (Map.Entry) mapSet.iterator().next();
                Class keyClazz = entry.getKey().getClass();
                Class valueClazz = entry.getValue().getClass();
                jsonSerializer = new JsonSerializer();
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
