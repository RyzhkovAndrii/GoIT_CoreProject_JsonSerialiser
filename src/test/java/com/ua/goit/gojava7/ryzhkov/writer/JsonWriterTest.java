package com.ua.goit.gojava7.ryzhkov.writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;

public class JsonWriterTest {

    private JsonWriter jsonWriter;
    private Writer stringWriter;

    @Before
    public void initTest() {
        stringWriter = new StringWriter();
        jsonWriter = new JsonWriter(stringWriter);
    }

    @After
    public void afterTest() {
        jsonWriter = null;
        stringWriter = null;
    }

    @Test
    public void writeObject() throws IOException {
        String testedString = "this is test string";
        String expectedString = "{\"this is test string\"}";
        jsonWriter.writeObjectBegin();
        jsonWriter.writeString(testedString);
        jsonWriter.writeSeparator();
        jsonWriter.writeObjectEnd();
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeArray() throws IOException {
        String expectedString = "[-1,1]";
        jsonWriter.writeArrayBegin();
        jsonWriter.writeNumber(-1);
        jsonWriter.writeSeparator();
        jsonWriter.writeNumber(1);
        jsonWriter.writeSeparator();
        jsonWriter.writeArrayEnd();
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeString() throws IOException {
        String testedString = "this is test string";
        String expectedString = "\"this is test string\"";
        jsonWriter.writeString(testedString);
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeStringWithDoubleQuotes() throws IOException {
        String testedString = "\"this is test string with double quotes (\")\"";
        String expectedString = "\"\\\"this is test string with double quotes (\\\")\\\"\"";
        jsonWriter.writeString(testedString);
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test (expected = NullPointerException.class)
    public void writeStringNull() {
        jsonWriter.writeString(null);
    }

    @Test
    public void writeNumberInteger() throws IOException {
        jsonWriter.writeNumber(6);
        jsonWriter.flush();
        assertEquals("6", stringWriter.toString());
    }

    @Test
    public void writeNumberLong() throws IOException {
        jsonWriter.writeNumber(6L);
        jsonWriter.flush();
        assertEquals("6", stringWriter.toString());
    }

    @Test
    public void writeNumberFloat() throws IOException {
        jsonWriter.writeNumber(6.0f);
        jsonWriter.flush();
        assertEquals("6.0", stringWriter.toString());
    }

    @Test
    public void writeNumberDouble() throws IOException {
        jsonWriter.writeNumber(6.0);
        jsonWriter.flush();
        assertEquals("6.0", stringWriter.toString());
    }

    @Test (expected = NullPointerException.class)
    public void writeNumberNull() {
        jsonWriter.writeNumber(null);
    }

    @Test
    public void writeSeparator() throws IOException {
        String expectedString = "\"testString1\",\"testString2\"";
        jsonWriter.writeString("testString1");
        jsonWriter.writeSeparator();
        jsonWriter.writeString("testString2");
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writePropertySeparator() throws IOException {
        String expectedString = "\"testName\":4";
        jsonWriter.writeString("testName");
        jsonWriter.writePropertySeparator();
        jsonWriter.writeNumber(4);
        jsonWriter.flush();
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeBooleanTrue() throws IOException {
        jsonWriter.writeBoolean(true);
        jsonWriter.flush();
        assertEquals("true", stringWriter.toString());
    }

    @Test
    public void writeBooleanFalse() throws IOException {
        jsonWriter.writeBoolean(false);
        jsonWriter.flush();
        assertEquals("false", stringWriter.toString());
    }

    @Test (expected = NullPointerException.class)
    public void writeBooleanNull() {
        jsonWriter.writeBoolean(null);
    }

    @Test
    public void writeNull() throws IOException {
        jsonWriter.writeNull();
        jsonWriter.flush();
        assertEquals("null", stringWriter.toString());
    }

}