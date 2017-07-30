package main.java.mapper;

import main.java.writer.JsonWriter;

/**
 * Created by hot shihov on 11.07.2017.
 */
public class StringMapper extends IJsonMapper {
    @Override
    public void write(Object obj, JsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeString((String) obj);
        }
        writer.flush();
    }
}
