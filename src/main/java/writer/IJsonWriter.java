package main.java.writer;

/**
 * Created by Andrii Ryzhkov on 07.07.2017.
 */

public interface IJsonWriter {

    /**
     * Add the begin of Object ({) in buffer
     */
    void writeObjectBegin();

    /**
     * Add the end of Object (}) in buffer.
     * If the previous symbol is comma (,) then delete it.
     */
    void writeObjectEnd();

    /**
     * Add the begin of Array ([) into buffer
     */
    void writeArrayBegin();

    /**
     * Add the end of Array (]) into buffer.
     * If the previous symbol is comma (,) then delete it.
     */
    void writeArrayEnd();

    /**
     * Add double quotes (") to begin and end of the string, if string has
     * double quotes (") in start and end, then add slash (/) to thees
     * double quotes ("), and then add this string into buffer.
     *
     * @param string is adding String
     * @throws NullPointerException if string is null
     */
    void writeString(String string) throws NullPointerException;

    /**
     * Add number into buffer
     *
     * @param number is adding Number
     * @throws NullPointerException if number is null
     */
    void writeNumber(Number number) throws NullPointerException;

    /**
     * Add comma (,) into buffer
     */
    void writeSeparator();

    /**
     * Add colon (:) into buffer
     */
    void writePropertySeparator();

    /**
     * Add true or false into buffer
     *
     * @param bool is adding Boolean
     * @throws NullPointerException if bool is null
     */
    void writeBoolean(Boolean bool) throws NullPointerException;

    /**
     * Add null into buffer
     */
    void writeNull();

    /**
     * Write buffer into writer
     */
    void flush();

}
