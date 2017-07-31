package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.serializer.JsonSerializer;
import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;

public abstract class AbstractMapper {

    protected JsonSerializer jsonSerializer;

    public void setJsonSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public abstract void write(Object obj, IJsonWriter writer);

}
