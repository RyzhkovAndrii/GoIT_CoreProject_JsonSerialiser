package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.serializer.JsonSerializer;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ObjectArrayMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private Object[] objectArray;

    @Before
    public void initTest() {
        mapper = new ObjectArrayMapper();
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
        JsonSerializer jsonSerializer = new JsonSerializer();
        mapper.setJsonSerializer(jsonSerializer);
    }

    @After
    public void afterTest() {
        mapper = null;
        writer = null;
        jsonWriter = null;
        objectArray = null;
    }

    @Test
    public void writeStringObjectArray() {
        objectArray = new String[4];
        objectArray[0] = "string in index 0";
        objectArray[1] = "string in index 1";
        objectArray[2] = null;
        objectArray[3] = "string in index 3";
        mapper.write(objectArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[" +
                "\"string in index 0\"," +
                "\"string in index 1\"," +
                "null," +
                "\"string in index 3\"]", writer.toString());
    }

    @Test (expected = ClassCastException.class)
    public void writeObjectArray() {
        objectArray = new Object[0];
        mapper.write(objectArray, jsonWriter);
    }

    @Test
    public void writeObjectArrayEmpty() {
        objectArray = new Object[0];
        mapper.write(objectArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
    }

    @Test
    public void writeObjectArrayNull() {
        objectArray = null;
        mapper.write(objectArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("null", writer.toString());
    }

}