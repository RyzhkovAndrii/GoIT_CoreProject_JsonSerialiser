package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;

public class StringMapper extends AbstractMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeString((String) obj);
        }
    }

}
