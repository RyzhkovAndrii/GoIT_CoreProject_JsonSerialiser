package main.java.mapper;

import main.java.writer.IJsonWriter;

public class NumberMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeNumber((Number) (obj));
        }
    }

}
