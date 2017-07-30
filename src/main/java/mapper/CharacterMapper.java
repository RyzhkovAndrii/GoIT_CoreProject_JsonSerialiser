package main.java.mapper;

import main.java.writer.IJsonWriter;

public class CharacterMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter jsonWriter) {
        if (obj == null) {
            jsonWriter.writeNull();
        } else {
            Character character = (Character) obj;
            int intCharacter = (int) character;
            jsonWriter.writeNumber(intCharacter);
        }
    }
}
