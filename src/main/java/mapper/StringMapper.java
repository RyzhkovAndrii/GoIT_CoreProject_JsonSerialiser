package main.java.mapper;

import main.java.writer.IJsonWriter;

public class StringMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeString((String) obj);
        }
    }

}
