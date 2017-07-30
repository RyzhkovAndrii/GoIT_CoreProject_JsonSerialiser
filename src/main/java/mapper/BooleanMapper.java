package main.java.mapper;

import main.java.writer.IJsonWriter;

public class BooleanMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        writer.writeBoolean((Boolean) obj);
        writer.flush();
    }
}
