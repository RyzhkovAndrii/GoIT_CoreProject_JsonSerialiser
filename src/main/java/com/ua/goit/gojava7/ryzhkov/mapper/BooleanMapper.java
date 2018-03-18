package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

public class BooleanMapper extends AbstractMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        writer.writeBoolean((Boolean) obj);
    }
}
