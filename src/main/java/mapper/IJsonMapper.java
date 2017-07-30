package main.java.mapper;

import main.java.writer.JsonWriter;

public abstract class IJsonMapper {

    public abstract void write(Object obj, JsonWriter writer);

}
