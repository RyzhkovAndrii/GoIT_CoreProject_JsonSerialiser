package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;
import goit.gojava7.group7.jsonserialiser.writer.JsonWriter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

public class CharacterMapperTest {

    private CharacterMapper mapper;
    private IJsonWriter jsonWriter;
    private StringWriter writer;
    private Character character;
    private char aChar;

    @Before
    public void initTest() {
        mapper = new CharacterMapper();
        writer = new StringWriter();
        jsonWriter = new JsonWriter(writer);
    }

    @After
    public void afterTest() {
        mapper = null;
        writer = null;
        jsonWriter = null;
        character = null;
    }

    @Test
    public void writeCharacterLikeSymbol() {
        character = 'a';
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("97", writer.toString());
    }

    @Test
    public void writeCharacterLikeNumber() {
        character = 97;
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("97", writer.toString());
    }

    @Test
    public void writeCharacterMin() {
        character = Character.MIN_VALUE;
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("0", writer.toString());
    }

    @Test
    public void writeCharacterMax() {
        character = Character.MAX_VALUE;
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("65535", writer.toString());
    }

    @Test
    public void writeCharLikeSymbol() {
        aChar = 'a';
        mapper.write(aChar, jsonWriter);
        jsonWriter.flush();
        assertEquals("97", writer.toString());
    }

    @Test
    public void writeCharLikeNumber() {
        aChar = 97;
        mapper.write(aChar, jsonWriter);
        jsonWriter.flush();
        assertEquals("97", writer.toString());
    }

    @Test
    public void writeCharacterNull() {
        character = null;
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("null", writer.toString());
    }

    @Test
    public void writeCharacterNegativeNumber() {
        character = (char) -1;
        mapper.write(character, jsonWriter);
        jsonWriter.flush();
        assertEquals("65535", writer.toString());
    }

}