package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.serializer.JsonSerializer;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

public abstract class AbstractMapper {

    protected JsonSerializer jsonSerializer;

    public void setJsonSerializer(JsonSerializer jsonSerializer) {
        this.jsonSerializer = jsonSerializer;
    }

    public abstract void write(Object obj, IJsonWriter writer);

}
