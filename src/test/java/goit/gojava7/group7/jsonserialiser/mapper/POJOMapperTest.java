package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.annotation.JsonIgnore;
import goit.gojava7.group7.jsonserialiser.annotation.JsonProperty;
import goit.gojava7.group7.jsonserialiser.serializer.JsonSerializer;
import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;
import goit.gojava7.group7.jsonserialiser.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class POJOMapperTest {

    private AbstractMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private JsonSerializer jsonSerializer;

    @Before
    public void initTest() {
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
        jsonSerializer = new JsonSerializer();
    }

    @After
    public void afterTest() {
        mapper = null;
        writer = null;
        jsonWriter = null;
    }

    @Test
    public void writeObject() {
        class TestClass {
            public int firstFieldInt = 0;
            public String secondFieldString = "string";
            public boolean thirdFieldBoolean = true;
            public Object nullField = null;
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{" +
                "\"firstFieldInt\":0," +
                "\"secondFieldString\":\"string\"," +
                "\"thirdFieldBoolean\":true," +
                "\"nullField\":null" +
                "}", writer.toString());
    }

    @Test
    public void writeObjectTransientFiled() {
        class TestClass {
            public transient int transientField = -100;
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{}", writer.toString());
    }

    @Test
    public void writeObjectPrivateFiled() {
        class TestClass {
            private int privateField = 100;
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{}", writer.toString());
    }

    @Test
    public void writeObjectJsonProperty() {
        class TestClass {
            @JsonProperty
            private int privateField = 100;
            @JsonProperty
            public transient int transientField = -100;
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{\"privateField\":100,\"transientField\":100}", writer.toString());
    }

    @Test
    public void writeObjectJsonPropertyName() {
        class TestClass {
            @JsonProperty (name = "newFieldName")
            public String oldFieldName = "oldName";
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{\"newFieldName\":\"oldName\"}", writer.toString());
    }

    @Test
    public void writeObjectJsonIgnore() {
        class TestClass {
            @JsonIgnore
            public int jsonIgnoredField = 100;
        }
        TestClass testClass = new TestClass();
        mapper = new POJOMapper(TestClass.class);
        mapper.setJsonSerializer(jsonSerializer);
        mapper.write(testClass, jsonWriter);
        jsonWriter.flush();
        assertEquals("{}", writer.toString());
    }

}