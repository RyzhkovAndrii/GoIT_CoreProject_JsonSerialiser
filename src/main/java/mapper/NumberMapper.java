package main.java.mapper;

import main.java.writer.IJsonWriter;

public class NumberMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        writer.writeNumber((Number) (obj));
        writer.flush();
    }

}
