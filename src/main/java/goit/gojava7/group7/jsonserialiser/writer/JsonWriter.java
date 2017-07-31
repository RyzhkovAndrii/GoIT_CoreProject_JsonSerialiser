package goit.gojava7.group7.jsonserialiser.writer;

import java.io.IOException;
import java.io.Writer;

public class JsonWriter implements IJsonWriter {

    private Writer writer;
    private StringBuilder buffer;

    public JsonWriter(Writer writer) {
        this.writer = writer;
        buffer = new StringBuilder();
    }

    private String escapeFirstAndLastDoubleQuotes(String string) {
        if (string.startsWith("\"")) {
            string = "\\" + string;
        }
        if (string.endsWith("\"")) {
            string = string.substring(0, string.length() - 1) + "\\\"";
        }
        return string;
    }

    protected boolean isLastSymbol(char symbol) {
        int length = buffer.length();
        return length != 0 && buffer.charAt(length - 1) == symbol;
    }

    protected void deleteLastSymbol() {
        int length = buffer.length();
        if (length != 0) {
            buffer.deleteCharAt(length - 1);
        }
    }

    protected void writeSymbol(char symbol) {
        buffer.append(symbol);
    }

    @Override
    public void writeObjectBegin() {
        writeSymbol('{');
    }

    @Override
    public void writeObjectEnd() {
        if (isLastSymbol(',')) deleteLastSymbol();
        writeSymbol('}');
    }

    @Override
    public void writeArrayBegin() {
        writeSymbol('[');
    }

    @Override
    public void writeArrayEnd() {
        if (isLastSymbol(',')) deleteLastSymbol();
        writeSymbol(']');
    }

    @Override
    public void writeString(String string) throws NullPointerException {
        if (string == null) {
            throw new NullPointerException();
        }
        writeSymbol('"');
        string = escapeFirstAndLastDoubleQuotes(string);
        buffer.append(string);
        writeSymbol('"');
    }

    @Override
    public void writeNumber(Number number) throws NullPointerException {
        if (number == null) {
            throw new NullPointerException();
        }
        buffer.append(number);
    }

    @Override
    public void writeSeparator() {
        writeSymbol(',');
    }

    @Override
    public void writePropertySeparator() {
        writeSymbol(':');
    }

    @Override
    public void writeBoolean(Boolean bool) throws NullPointerException {
        if (bool == null) {
            throw new NullPointerException();
        }
        buffer.append(bool ? "true" : "false");
    }

    @Override
    public void writeNull() {
        buffer.append("null");
    }

    @Override
    public void flush() {
        try {
            writer.write(buffer.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer.setLength(0);
    }
}
