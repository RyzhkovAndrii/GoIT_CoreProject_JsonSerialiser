package goit.gojava7.group7.jsonserialiser.writer;

public interface IJsonWriter {

    void writeObjectBegin();

    void writeObjectEnd();

    void writeArrayBegin();

    void writeArrayEnd();

    void writeString(String string) throws NullPointerException;

    void writeNumber(Number number) throws NullPointerException;

    void writeSeparator();

    void writePropertySeparator();

    void writeBoolean(Boolean bool) throws NullPointerException;

    void writeNull();

    void flush();

}
