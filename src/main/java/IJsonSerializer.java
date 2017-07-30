package main.java;

import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Created by hot shihov on 07.07.2017.
 */

public interface IJsonSerializer {

    boolean isIndent();

    void setIndent(boolean indent, int indentSize);

    String serialize(Object obj);

    void serialize(Object obj, OutputStream stream);

    void serialize(Object obj, OutputStream stream, Charset charset);

    void serialize(Object obj, Writer writer);


}
