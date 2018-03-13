package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

public class NumberMapper extends AbstractMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            writer.writeNumber((Number) (obj));
        }
    }

}
