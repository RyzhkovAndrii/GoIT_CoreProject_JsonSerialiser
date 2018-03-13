package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.serializer.JsonSerializer;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class CollectionMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private Collection collection;

    @Before
    public void initTest() {
        mapper = new CollectionMapper();
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
        collection = null;
    }

    @Test
    public void writeCollection() {
        collection = new ArrayList<Number>();
        collection.add(0);
        collection.add(-1);
        collection.add(1);
        collection.add(Long.MIN_VALUE);
        collection.add(Long.MAX_VALUE);
        collection.add(Double.MIN_VALUE);
        collection.add(Double.MAX_VALUE);
        collection.add(null);
        mapper.write(collection, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1,-9223372036854775808,9223372036854775807,4.9E-324,1.7976931348623157E308,null]",
                writer.toString());
    }

    @Test
    public void writeCollectionEmpty() {
        collection = new ArrayList();
        mapper.write(collection, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
    }

    @Test
    public void writeCollectionNull() {
        collection = null;
        mapper.write(collection, jsonWriter);
        jsonWriter.flush();
        assertEquals("null", writer.toString());
    }

}