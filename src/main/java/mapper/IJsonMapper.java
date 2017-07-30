package main.java.mapper;

import main.java.serializer.JsonSerializer;
import main.java.writer.IJsonWriter;

public abstract class IJsonMapper {

    protected JsonSerializer jsonSerializer;

    public void setJsonSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public abstract void write(Object obj, IJsonWriter writer);

}
