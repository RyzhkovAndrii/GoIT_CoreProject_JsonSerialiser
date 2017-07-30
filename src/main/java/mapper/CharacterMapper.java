package main.java.mapper;

import main.java.writer.JsonWriter;

/**
 * Created by Andrii Ryzhkov on 29.07.2017.
 */
public class CharacterMapper extends IJsonMapper {

    @Override
    public void write(Object obj, JsonWriter jsonWriter) {
        Character character = (Character) obj;
        int intCharacter = (int) character;
        jsonWriter.writeNumber(intCharacter);
    }
}
