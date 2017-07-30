package main.java.mapper;

import main.java.writer.JsonWriter;

public class CharacterMapper extends IJsonMapper {

    @Override
    public void write(Object obj, JsonWriter jsonWriter) {
        Character character = (Character) obj;
        int intCharacter = (int) character;
        jsonWriter.writeNumber(intCharacter);
    }
}
