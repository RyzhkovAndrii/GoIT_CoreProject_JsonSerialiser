package main.java.mapper;

import main.java.writer.JsonWriter;

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
