package main.java.mapper;

import main.java.writer.IJsonWriter;

import java.util.Arrays;

public class ObjectArrayMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {
        if (obj == null) {
            writer.writeNull();
        } else {
            Class clazz = obj.getClass().getComponentType();
            Object[] array = (Object[]) obj;
            IJsonMapper elementMapper = jsonSerializer.getMapper(clazz);
            writer.writeArrayBegin();
            Arrays.stream(array).forEach((element) -> {
                elementMapper.write(element, writer);
                writer.writeSeparator();
            });
            writer.writeArrayEnd();
        }
    }

}













