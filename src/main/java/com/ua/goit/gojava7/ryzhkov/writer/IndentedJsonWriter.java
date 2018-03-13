package com.ua.goit.gojava7.ryzhkov.writer;

import java.io.Writer;

public class IndentedJsonWriter extends JsonWriter {

    private static final int DEFAULT_INDENT_SIZE = 3;

    private int indentSize;
    private int objectLevel;
    private int arrayLevel;

    public IndentedJsonWriter(Writer writer, int indentSize) {
        super(writer);
        setIndentSize(indentSize);
        objectLevel = 0;
        arrayLevel = 0;
    }

    public IndentedJsonWriter(Writer writer) {
        this(writer, DEFAULT_INDENT_SIZE);
    }

    private void writeNewLineWithIndent() {
        writeSymbol('\n');
        writeSpaces((objectLevel + arrayLevel) * indentSize);
    }

    private void writeSpaces(int spaceCount) {
        for (int currentCount = spaceCount; currentCount > 0; currentCount--) {
            writeSymbol(' ');
        }
    }

    private boolean isInObject() {
        return objectLevel > 0;
    }

    private boolean isInArray() {
        return arrayLevel > 0;
    }

    public void setIndentSize(int indentSize) {
        if (indentSize < 0) {
            this.indentSize = DEFAULT_INDENT_SIZE;
        } else {
            this.indentSize = indentSize;
        }
    }

    @Override
    public void writeObjectBegin() {
        super.writeObjectBegin();
        objectLevel++;
        writeNewLineWithIndent();
    }

    @Override
    public void writeObjectEnd() {
        objectLevel--;
        while (isLastSymbol(' ') || isLastSymbol('\n')) {
            deleteLastSymbol();
        }
        if (isLastSymbol(',')) deleteLastSymbol();
        writeNewLineWithIndent();
        super.writeObjectEnd();
    }

    @Override
    public void writeArrayBegin() {
        super.writeArrayBegin();
        arrayLevel++;
    }

    @Override
    public void writeArrayEnd() {
        while (isLastSymbol(' ')) {
            deleteLastSymbol();
        }
        super.writeArrayEnd();
        arrayLevel--;
    }

    @Override
    public void writeSeparator() {
        super.writeSeparator();
        if (isInObject() && !isInArray()) {
            writeNewLineWithIndent();
        } else {
            writeSpaces(1);
        }
    }

    @Override
    public void writePropertySeparator() {
        writeSpaces(1);
        super.writePropertySeparator();
        writeSpaces(1);
    }
}
