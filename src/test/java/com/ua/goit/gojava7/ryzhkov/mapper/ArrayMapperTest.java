package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.serializer.JsonSerializer;
import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;
import com.ua.goit.gojava7.ryzhkov.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class ArrayMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private Object[] objectArray;

    @Before
    public void initTest() {
        mapper = new ArrayMapper();
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
        JsonSerializer jsonSerializer = new JsonSerializer();
        mapper.setJsonSerializer(jsonSerializer);
    }

    @After
    public void afterTest() {
        objectArray = null;
        mapper = null;
        writer = null;
        jsonWriter = null;
    }

    @Test
    public void writeIntPrimitiveArray() {
        int[] intArray = new int[5];
        intArray[0] = 0;
        intArray[1] = -1;
        intArray[2] = 1;
        intArray[3] = Integer.MAX_VALUE;
        intArray[4] = Integer.MIN_VALUE;
        mapper.write(intArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Integer.MAX_VALUE + "," + Integer.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeBytePrimitiveArray() {
        byte[] byteArray = new byte[5];
        byteArray[0] = 0;
        byteArray[1] = -1;
        byteArray[2] = 1;
        byteArray[3] = Byte.MAX_VALUE;
        byteArray[4] = Byte.MIN_VALUE;
        mapper.write(byteArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Byte.MAX_VALUE + "," + Byte.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeShortPrimitiveArray() {
        short[] shortArray = new short[5];
        shortArray[0] = 0;
        shortArray[1] = -1;
        shortArray[2] = 1;
        shortArray[3] = Short.MAX_VALUE;
        shortArray[4] = Short.MIN_VALUE;
        mapper.write(shortArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Short.MAX_VALUE + "," + Short.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeLongPrimitiveArray() {
        long[] longArray = new long[5];
        longArray[0] = 0;
        longArray[1] = -1;
        longArray[2] = 1;
        longArray[3] = Long.MAX_VALUE;
        longArray[4] = Long.MIN_VALUE;
        mapper.write(longArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Long.MAX_VALUE + "," + Long.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeDoublePrimitiveArray() {
        double[] doubleArray = new double[5];
        doubleArray[0] = 0;
        doubleArray[1] = -1;
        doubleArray[2] = 1;
        doubleArray[3] = Double.MAX_VALUE;
        doubleArray[4] = Double.MIN_VALUE;
        mapper.write(doubleArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0.0,-1.0,1.0," + Double.MAX_VALUE + "," + Double.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeFloatPrimitiveArray() {
        float[] floatArray = new float[5];
        floatArray[0] = 0;
        floatArray[1] = -1;
        floatArray[2] = 1;
        floatArray[3] = Float.MAX_VALUE;
        floatArray[4] = Float.MIN_VALUE;
        mapper.write(floatArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0.0,-1.0,1.0," + Float.MAX_VALUE + "," + Float.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeCharPrimitiveArray() {
        char[] charArray = new char[5];
        charArray[0] = 0;
        charArray[1] = 'a';
        charArray[2] = 1;
        charArray[3] = Character.MAX_VALUE;
        charArray[4] = Character.MIN_VALUE;
        mapper.write(charArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,97,1," + (int) Character.MAX_VALUE + "," + (int) Character.MIN_VALUE + "]",
                writer.toString());
    }

    @Test
    public void writeBooleanPrimitiveArray() {
        boolean[] booleanArray = new boolean[2];
        booleanArray[0] = true;
        booleanArray[1] = false;
        mapper.write(booleanArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[true,false]", writer.toString());
    }

    @Test
    public void writePrimitiveArrayEmpty() {
        int[] emptyArray = new int[0];
        mapper.write(emptyArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
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

    @Test
    public void writeObjectArrayEmpty() {
        objectArray = new Object[0];
        mapper.write(objectArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
    }

    @Test(expected = NullPointerException.class)
    public void writeObjectArrayNull() {
        mapper.write(null, jsonWriter);
    }

}
