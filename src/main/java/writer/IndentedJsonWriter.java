package main.java.writer;

import java.io.Writer;

/**
 * Created by Andrii Ryzhkov on 10.07.2017.
 */

public class IndentedJsonWriter extends JsonWriter {

    private static final int DEFAULT_INDENT_SIZE = 1;

    private int indentSize;
    private int currentLevel;
    private boolean isInObject;
    private boolean isInArray;

    public IndentedJsonWriter(Writer writer, int indentSize) {
        super(writer);
        setIndentSize(indentSize);
        currentLevel = 0;
    }

    public IndentedJsonWriter(Writer writer) {
        this(writer, DEFAULT_INDENT_SIZE);
    }

    private void writeNewLineWithIndent(int indentLength) {
        writeSymbol('\n');
        writeSpaces(indentLength);
    }

    private void writeSpaces(int spaceCount) {
        for (int currentCount = spaceCount; currentCount > 0; currentCount--) {
            writeSymbol(' ');
        }
    }

    private int indentLength() {
        return currentLevel * indentSize;
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
        currentLevel++;
        writeNewLineWithIndent(indentLength());
        isInObject = true;
    }

    @Override
    public void writeObjectEnd() {
        currentLevel--;
        while (isLastSymbol(' ') || isLastSymbol('\n')) {
            deleteLastSymbol();
        }
        if (isLastSymbol(',')) deleteLastSymbol();
        writeNewLineWithIndent(indentLength());
        super.writeObjectEnd();
        isInObject = false;
    }

    @Override
    public void writeArrayBegin() {
        super.writeArrayBegin();
        isInArray = true;
    }

    @Override
    public void writeArrayEnd() {
        while (isLastSymbol(' ')) {
            deleteLastSymbol();
        }
        super.writeArrayEnd();
        isInArray = false;
    }

    @Override
    public void writeSeparator() {
        super.writeSeparator();
        if (isInObject && !isInArray) {
            writeNewLineWithIndent(indentLength());
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
