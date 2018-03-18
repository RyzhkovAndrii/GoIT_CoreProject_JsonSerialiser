package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.serializer.JsonSerializer;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private Map map;

    @Before
    public void initTest() {
        mapper = new MapMapper();
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
        map = null;
    }

    @Test
    public void writeMap() {
        map = new LinkedHashMap<Integer, String>();
        map.put(0, "this is 0");
        map.put(-1, "this is -1");
        map.put(1, "this is 1");
        map.put(Long.MIN_VALUE, "this is Long.MIN_VALUE");
        map.put(Long.MAX_VALUE, "this is Long.MAX_VALUE");
        map.put(Double.MIN_VALUE, "this is Double.MIN_VALUE");
        map.put(Double.MAX_VALUE, "this is Double.MAX_VALUE");
        map.put(null, "this is null");
        mapper.write(map, jsonWriter);
        jsonWriter.flush();
        assertEquals("[" +
                        "0:\"this is 0\"," +
                        "-1:\"this is -1\"," +
                        "1:\"this is 1\"," +
                        "-9223372036854775808:\"this is Long.MIN_VALUE\"," +
                        "9223372036854775807:\"this is Long.MAX_VALUE\"," +
                        "4.9E-324:\"this is Double.MIN_VALUE\"," +
                        "1.7976931348623157E308:\"this is Double.MAX_VALUE\"," +
                        "null:\"this is null\"" +
                        "]",
                writer.toString());
    }

    @Test
    public void writeMapEmpty() {
        map = new HashMap();
        mapper.write(map, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
    }

    @Test(expected = NullPointerException.class)
    public void writeMapNull() {
        mapper.write(null, jsonWriter);
    }

}