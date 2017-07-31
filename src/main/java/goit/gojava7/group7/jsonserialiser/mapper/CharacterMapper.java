package goit.gojava7.group7.jsonserialiser.mapper;

import goit.gojava7.group7.jsonserialiser.writer.IJsonWriter;

public class CharacterMapper extends AbstractMapper {

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
