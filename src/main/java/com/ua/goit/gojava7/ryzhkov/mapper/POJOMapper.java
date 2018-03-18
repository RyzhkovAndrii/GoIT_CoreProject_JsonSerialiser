package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.annotation.JsonIgnore;
import com.ua.goit.gojava7.ryzhkov.annotation.JsonProperty;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class POJOMapper extends AbstractMapper {

    private List<Field> fieldsList;

    public POJOMapper(Class clazz) {
        this.fieldsList = getSerialisingFieldsList(clazz);
    }

    private List<Field> getSerialisingFieldsList(Class clazz) {
        Set<String> namesSet = new HashSet<>();
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(this::isSerialising)
                .filter(field -> namesSet.add(getNameOfFieldWhitAnnotation(field)))
                .collect(Collectors.toList());
    }

    private String getNameOfFieldWhitAnnotation(Field field) {
        return hasJsonProperty(field) && !field.getAnnotation(JsonProperty.class).name().equals("")
                ? field.getAnnotation(JsonProperty.class).name()
                : field.getName();
    }

    private boolean isPublic(Field field) {
        return Modifier.isPublic(field.getModifiers());
    }

    private boolean hasJsonProperty(Field field) {
        return field.isAnnotationPresent(JsonProperty.class);
    }

    private boolean isSerialising(Field field) {
        boolean isNotTransient = !Modifier.isTransient(field.getModifiers());
        boolean isNotStatic = !Modifier.isStatic(field.getModifiers());
        boolean hasNotJsonIgnore = !field.isAnnotationPresent(JsonIgnore.class);
        return isNotStatic
                && ((isPublic(field) && isNotTransient) || hasJsonProperty(field))
                && hasNotJsonIgnore;
    }

    public void write(Object obj, IJsonWriter jsonWriter) {
        jsonWriter.writeObjectBegin();
        fieldsList.forEach(field -> {
            jsonWriter.writeString(getNameOfFieldWhitAnnotation(field));
            jsonWriter.writePropertySeparator();
            try {
                if (!isPublic(field)) {
                    field.setAccessible(true);
                    jsonSerializer.serialize(field.get(obj), jsonWriter);
                    field.setAccessible(false);
                } else {
                    jsonSerializer.serialize(field.get(obj), jsonWriter);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            jsonWriter.writeSeparator();
        });
        jsonWriter.writeObjectEnd();
    }

}
