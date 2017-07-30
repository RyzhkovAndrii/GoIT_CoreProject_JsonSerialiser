package main.java.mapper;

import main.java.writer.JsonWriter;

/**
 * Created by hot shihov on 11.07.2017.
 */
public class BooleanMapper extends IJsonMapper {
    @Override
    public void write(Object obj, JsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeBoolean((Boolean) obj);
        }
        writer.flush();
    }
}
