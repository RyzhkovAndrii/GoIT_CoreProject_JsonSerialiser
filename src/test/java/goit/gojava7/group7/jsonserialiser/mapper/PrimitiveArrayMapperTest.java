package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.serializer.JsonSerializer;
import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;
import goit.gojava7.group7.jsonserialiser.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class PrimitiveArrayMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;

    @Before
    public void initTest() {
        mapper = new PrimitiveArrayMapper();
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
    }

    @Test
    public void writeIntPrimitiveArray() {
        int[] primitiveArray = new int[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Integer.MAX_VALUE;
        primitiveArray[4] = Integer.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Integer.MAX_VALUE + "," + Integer.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeBytePrimitiveArray() {
        byte[] primitiveArray = new byte[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Byte.MAX_VALUE;
        primitiveArray[4] = Byte.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Byte.MAX_VALUE + "," + Byte.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeShortPrimitiveArray() {
        short[] primitiveArray = new short[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Short.MAX_VALUE;
        primitiveArray[4] = Short.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Short.MAX_VALUE + "," + Short.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeLongPrimitiveArray() {
        long[] primitiveArray = new long[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Long.MAX_VALUE;
        primitiveArray[4] = Long.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,-1,1," + Long.MAX_VALUE + "," + Long.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeDoublePrimitiveArray() {
        double[] primitiveArray = new double[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Double.MAX_VALUE;
        primitiveArray[4] = Double.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0.0,-1.0,1.0," + Double.MAX_VALUE + "," + Double.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeFloatPrimitiveArray() {
        float[] primitiveArray = new float[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = -1;
        primitiveArray[2] = 1;
        primitiveArray[3] = Float.MAX_VALUE;
        primitiveArray[4] = Float.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0.0,-1.0,1.0," + Float.MAX_VALUE + "," + Float.MIN_VALUE + "]", writer.toString());
    }

    @Test
    public void writeCharPrimitiveArray() {
        char[] primitiveArray = new char[5];
        primitiveArray[0] = 0;
        primitiveArray[1] = 'a';
        primitiveArray[2] = 1;
        primitiveArray[3] = Character.MAX_VALUE;
        primitiveArray[4] = Character.MIN_VALUE;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[0,97,1," + (int)Character.MAX_VALUE + "," + (int)Character.MIN_VALUE + "]",
                writer.toString());
    }

    @Test
    public void writeBooleanPrimitiveArray() {
        boolean[] primitiveArray = new boolean[2];
        primitiveArray[0] = true;
        primitiveArray[1] = false;
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[true,false]", writer.toString());
    }

    @Test
    public void writePrimitiveArrayEmpty() {
        int[] primitiveArray = new int[0];
        mapper.write(primitiveArray, jsonWriter);
        jsonWriter.flush();
        assertEquals("[]", writer.toString());
    }

}