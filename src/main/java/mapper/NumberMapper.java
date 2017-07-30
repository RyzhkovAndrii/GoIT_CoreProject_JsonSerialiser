package main.java.mapper;

import main.java.writer.JsonWriter;

/**
 * Created by hot shihov on 10.07.2017.
 */
public class NumberMapper extends IJsonMapper {
    @Override
    public void write(Object obj, JsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeNumber((Number) (obj));
        }
        writer.flush();
    }
}
