package com.ua.goit.gojava7.ryzhkov.mapper;

import com.ua.goit.gojava7.ryzhkov.writer.IJsonWriter;

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
