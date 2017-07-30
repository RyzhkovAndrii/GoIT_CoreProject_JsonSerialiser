package main.java.mapper;

import main.java.writer.IJsonWriter;

public abstract class IJsonMapper {

    public abstract void write(Object obj, IJsonWriter writer);

}
