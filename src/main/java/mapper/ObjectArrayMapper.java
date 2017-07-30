package main.java.mapper;

import main.java.JsonSerializer;
import main.java.writer.JsonWriter;

import java.util.Arrays;

public class ObjectArrayMapper extends IJsonMapper {

    @Override
    public void write(Object obj, JsonWriter writer) {
        JsonSerializer jsonSerializer = new JsonSerializer();
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













