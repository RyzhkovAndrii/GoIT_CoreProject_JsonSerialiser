package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

import static java.lang.reflect.Array.get;
import static java.lang.reflect.Array.getLength;

public class ArrayMapper extends AbstractMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        writer.writeArrayBegin();
        int length = getLength(obj);
        for (int i = 0; i < length; i++) {
            jsonSerializer.serialize(get(obj, i), writer);
            if (i != length - 1) writer.writeSeparator();
        }
        writer.writeArrayEnd();
    }

}
