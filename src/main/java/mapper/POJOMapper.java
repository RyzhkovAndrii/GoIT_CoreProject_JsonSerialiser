package main.java.mapper;

import main.java.annotation.JsonIgnore;
import main.java.annotation.JsonProperty;
import main.java.writer.IJsonWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.stream.Collectors;

public class POJOMapper extends IJsonMapper {

    private List<Field> fieldsList;

    public POJOMapper(Class clazz) {
        this.fieldsList = getFieldsList(clazz);
    }

    private List<Field> getFieldsList(Class clazz) {
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
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
            jsonWriter.writeObjectBegin();
            for (Field field : fieldsList) {
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
            }
            jsonWriter.writeObjectEnd();
        }
    }

}
