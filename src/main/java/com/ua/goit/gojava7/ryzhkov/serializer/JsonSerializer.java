package com.ua.goit.gojava7.ryzhkov.serializer;

import com.ua.goit.gojava7.ryzhkov.mapper.AbstractMapper;
import com.ua.goit.gojava7.ryzhkov.mapper.MappersCash;
import com.ua.goit.gojava7.ryzhkov.mapper.POJOMapper;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.IndentedJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.JsonWriter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Map;

public class JsonSerializer implements IJsonSerializer {

    private static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private volatile MappersCash mappersCache;
    private boolean indent;
    private int indentSize;

    public JsonSerializer() {
        indent = false;
        mappersCache = MappersCash.getInstance();
    }

    private AbstractMapper getMapper(Class clazz) {
        AbstractMapper mapper = mappersCache.getMapper(clazz);
        if (mapper == null) {
            if (clazz.isArray()) {
                mapper = mappersCache.getMapper(Object[].class);
            } else if (Number.class.isAssignableFrom(clazz)) {
                mapper = mappersCache.getMapper(Number.class);
            } else if (Boolean.class.isAssignableFrom(clazz)) {
                mapper = mappersCache.getMapper(Boolean.class);
            } else if (Collection.class.isAssignableFrom(clazz)) {
                mapper = mappersCache.getMapper(Collection.class);
            } else if (Map.class.isAssignableFrom(clazz)) {
                mapper = mappersCache.getMapper(Map.class);
            } else {
                mapper = getNewPOJOMapper(clazz);
            }
        }
        return mapper;
    }

    private AbstractMapper getNewPOJOMapper(Class clazz) {
        synchronized (clazz.getName()) {
            AbstractMapper mapper = mappersCache.getMapper(clazz);
            if (mapper == null) {
                mapper = new POJOMapper(clazz);
                mappersCache.putMapper(clazz, mapper);
            }
            return mapper;
        }
    }

    public void serialize(Object obj, IJsonWriter jsonWriter) {
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
            AbstractMapper mapper = getMapper(obj.getClass());
            mapper.setJsonSerializer(this);
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