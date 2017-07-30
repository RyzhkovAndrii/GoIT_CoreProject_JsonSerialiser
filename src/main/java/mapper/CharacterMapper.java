package main.java.mapper;

import main.java.writer.IJsonWriter;

public class CharacterMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter jsonWriter) {
        Character character = (Character) obj;
        int intCharacter = (int) character;
        jsonWriter.writeNumber(intCharacter);
    }
}
