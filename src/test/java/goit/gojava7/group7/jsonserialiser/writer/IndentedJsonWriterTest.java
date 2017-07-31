package goit.gojava7.group7.jsonserialiser.writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.junit.Assert.assertEquals;

public class IndentedJsonWriterTest {

    private IndentedJsonWriter jsonWriter;
    private Writer stringWriter;

    private String fillArrayTestString = "[1, \"testArrayString\"]";

    private void fillArray() throws IOException {
        jsonWriter.writeArrayBegin();
        jsonWriter.writeNumber(1);
        jsonWriter.writeSeparator();
        jsonWriter.writeString("testArrayString");
        jsonWriter.writeSeparator();
        jsonWriter.writeArrayEnd();
        jsonWriter.flush();
    }

    private void fillObject(String testString1, String  testString2) throws IOException {
        jsonWriter.writeObjectBegin();
        jsonWriter.writeString(testString1);
        jsonWriter.writeSeparator();
        jsonWriter.writeString(testString2);
        jsonWriter.writeSeparator();
        jsonWriter.writeObjectEnd();
        jsonWriter.flush();
    }

    @Before
    public void initTest() {
        stringWriter = new StringWriter();
        jsonWriter = new IndentedJsonWriter(stringWriter, 2);
    }

    @After
    public void afterTest() {
        jsonWriter = null;
        stringWriter = null;
    }

    @Test
    public void writeSeparatorObject() throws IOException {
        fillObject("testString1", "testString2");
        jsonWriter.flush();
        String expectedString = "{\n"
                + "  \"testString1\",\n"
                + "  \"testString2\"\n"
                + "}";
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeArray() throws IOException {
        fillArray();
        assertEquals(fillArrayTestString, stringWriter.toString());
    }

    @Test
    public void writeSeparatorObjectArray() throws IOException {
        jsonWriter.writeObjectBegin();
        fillArray();
        jsonWriter.writeObjectEnd();
        jsonWriter.flush();
        String expectedString = "{\n" + "  " + fillArrayTestString + "\n}";
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writeSeparatorObjectArrayObject() throws IOException {
        jsonWriter.writeObjectBegin();
        jsonWriter.writeString("testArray");
        jsonWriter.writePropertySeparator();
        jsonWriter.writeArrayBegin();
        fillObject("testString1", "testString2");
        jsonWriter.writeSeparator();
        fillObject("testString3", "testString4");
        jsonWriter.writeSeparator();
        jsonWriter.writeArrayEnd();
        jsonWriter.writeObjectEnd();
        jsonWriter.flush();
        String expectedString = "{\n"
                + "  \"testArray\" : [{\n"
                + "    \"testString1\", \"testString2\"\n"
                + "  }, {\n"
                + "    \"testString3\", \"testString4\"\n"
                + "  }]\n"
                + "}";
        assertEquals(expectedString, stringWriter.toString());
    }

    @Test
    public void writePropertySeparator() throws IOException {
        jsonWriter.writeString("testName");
        jsonWriter.writePropertySeparator();
        jsonWriter.writeNumber(4);
        jsonWriter.flush();
        String expectedString = "\"testName\" : 4";
        assertEquals(expectedString, stringWriter.toString());
    }

}