package main.java.mapper;

import main.java.writer.JsonWriter;

/**
 * Created by hot shihov on 07.07.2017.
 */
public abstract class IJsonMapper<T> {
    public abstract void write(Object obj, JsonWriter writer);

}
