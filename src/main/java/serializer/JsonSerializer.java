package main.java.serializer;

import main.java.mapper.IJsonMapper;
import main.java.mapper.MappersCash;
import main.java.mapper.POJOMapper;
import main.java.writer.IJsonWriter;
import main.java.writer.IndentedJsonWriter;
import main.java.writer.JsonWriter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

public class JsonSerializer implements IJsonSerializer {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private MappersCash mappersCache;
    private boolean indent;
    private int indentSize;

    public JsonSerializer() {
        indent = false;
        mappersCache = MappersCash.getInstance();
    }

    private String getMapperRequest(Class clazz) {
        if (Number.class.isAssignableFrom(clazz)) {
            return MappersCash.NUMBER_MAPPER_NAME;
        } else if (clazz.equals(String.class)) {
            return MappersCash.STRING_MAPPER_NAME;
        } else if (Boolean.class.isAssignableFrom(clazz)) {
            return MappersCash.BOOLEAN_MAPPER_NAME;
        } else if (clazz.isArray()) {
            return clazz.getComponentType().isPrimitive()
                    ? MappersCash.PRIMITIVE_ARRAY_MAPPER_NAME
                    : MappersCash.OBJECT_ARRAY_MAPPER_NAME;
        } else if (java.util.Collection.class.isAssignableFrom(clazz)) {
            return MappersCash.COLLECTION_MAPPER_NAME;
        } else if (java.util.Map.class.isAssignableFrom(clazz)) {
            return MappersCash.MAP_MAPPER_NAME;
        } else if (clazz.equals(Character.class)) {
            return MappersCash.CHARACTER_MAPPER_NAME;
        } else {
            return clazz.getName();
        }
    }

    public IJsonMapper getMapper(Class clazz) {
        String mapperRequest = getMapperRequest(clazz);
        IJsonMapper mapper = mappersCache.getMapper(mapperRequest);
        if (mapper == null) {
            synchronized (this) {
                IJsonMapper newMapper = new POJOMapper(clazz);
                mappersCache.putMapper(clazz.getName(), newMapper);
                newMapper.setJsonSerializer(this);
                return newMapper;
            }
        } else {
            mapper.setJsonSerializer(this);
            return mapper;
        }
    }

    public void serialize(Object obj, IJsonWriter jsonWriter) {
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
            IJsonMapper mapper = getMapper(obj.getClass());
            mapper.write(obj, jsonWriter);
        }
        jsonWriter.flush();
    }

    @Override
    public boolean isIndent() {
        return indent;
    }

    @Override
    public void setIndent(boolean indent, int indentSize) {
        this.indent = indent;
        this.indentSize = indentSize;
    }

    @Override
    public String serialize(Object obj) {
        Writer writer = new StringWriter();
        serialize(obj, writer);
        return writer.toString();
    }

    @Override
    public void serialize(Object obj, OutputStream stream) {
        serialize(obj, new OutputStreamWriter(stream, DEFAULT_CHARSET));
    }

    @Override
    public void serialize(Object obj, OutputStream stream, Charset charset) {
        serialize(obj, new OutputStreamWriter(stream, charset));
    }

    @Override
    public void serialize(Object obj, Writer writer) {
        JsonWriter jsonWriter = isIndent()
                ? new IndentedJsonWriter(writer, indentSize)
                : new JsonWriter(writer);
        serialize(obj, jsonWriter);
    }
}