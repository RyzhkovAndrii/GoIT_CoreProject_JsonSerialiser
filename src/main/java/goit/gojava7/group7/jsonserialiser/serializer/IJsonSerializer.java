package goit.gojava7.group7.jsonserialiser.serializer;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

public interface IJsonSerializer {

    boolean isIndent();

    void setIndent(boolean indent, int indentSize);

    String serialize(Object obj);

    void serialize(Object obj, OutputStream stream);

    void serialize(Object obj, OutputStream stream, Charset charset);

    void serialize(Object obj, Writer writer);

}
