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
        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(this::isSerialising)
                .collect(Collectors.toList());
        fields = filterFieldListByNameFromAnnotation(fields);
        return fields;
    }

    private List<Field> filterTheSameAnnotationName(List<Field> fields) {
        Set<String> annotationNamesList = new HashSet<>();
        Iterator<Field> iterator = fields.iterator();
        while (iterator.hasNext()) {
            Field field = iterator.next();
            if (hasJsonProperty(field)) {
                String annotationName = field.getAnnotation(JsonProperty.class).value();
                if (annotationNamesList.contains(annotationName)) {
                    iterator.remove();
                } else {
                    annotationNamesList.add(annotationName);
                }
            }
        }
        return fields;
    }

    private List<Field> filterFieldListByNameFromAnnotation(List<Field> fields) {
        fields = filterTheSameAnnotationName(fields);
        Set<String> fieldsNamesFromAnnotations = new HashSet<>();
        for (Field field : fields) {
            if (hasJsonProperty(field)) {
                String annotationName = field.getAnnotation(JsonProperty.class).value();
                if (!annotationName.equals(field.getName())) {
                    fieldsNamesFromAnnotations.add(field.getAnnotation(JsonProperty.class).value());
                }
            }
        }
        fields = fields.stream()
                .filter(field -> !fieldsNamesFromAnnotations.contains(field.getName()))
                .collect(Collectors.toList());
        return fields;
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
        return isNotStatic && ((isPublic(field) && isNotTransient) || hasJsonProperty(field)) && hasNotJsonIgnore;
    }

    public void write(Object obj, IJsonWriter jsonWriter) {
        jsonWriter.writeObjectBegin();
        for (Field field : fieldsList) {
            String name = hasJsonProperty(field)
                    ? field.getAnnotation(JsonProperty.class).value()
                    : field.getName();
            jsonWriter.writeString(name);
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
