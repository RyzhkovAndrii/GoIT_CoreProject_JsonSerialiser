package main.java.mapper;

import main.java.JsonSerializer;
import main.java.writer.IJsonWriter;

public class PrimitiveArrayMapper extends IJsonMapper {

    @Override
    public void write(Object obj, IJsonWriter writer) {

        Class elementClass = obj.getClass().getComponentType();
        Number[] numbers;

        switch (elementClass.getCanonicalName()) {

            case "int":
                int[] intArray = (int[]) obj;
                numbers = new Number[intArray.length];
                for (int i = 0; i < intArray.length; i++) {
                    numbers[i] = intArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "byte":
                byte[] byteArray = (byte[]) obj;
                numbers = new Number[byteArray.length];
                for (int i = 0; i < byteArray.length; i++) {
                    numbers[i] = byteArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "short":
                short[] shortArray = (short[]) obj;
                numbers = new Number[shortArray.length];
                for (int i = 0; i < shortArray.length; i++) {
                    numbers[i] = shortArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "long":
                long[] longArray = (long[]) obj;
                numbers = new Number[longArray.length];
                for (int i = 0; i < longArray.length; i++) {
                    numbers[i] = longArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "double":
                double[] doubleArray = (double[]) obj;
                numbers = new Number[doubleArray.length];
                for (int i = 0; i < doubleArray.length; i++) {
                    numbers[i] = doubleArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "float":
                float[] floatArray = (float[]) obj;
                numbers = new Number[floatArray.length];
                for (int i = 0; i < floatArray.length; i++) {
                    numbers[i] = floatArray[i];
                }
                jsonSerializer.serialize(numbers, writer);
                break;

            case "char":
                char[] charArray = (char[]) obj;
                Character[] characters = new Character[charArray.length];
                for (int i = 0; i <charArray.length; i++) {
                    characters[i] = charArray[i];
                }
                jsonSerializer.serialize(characters, writer);
                break;

            case "boolean":
                boolean[] booleanArray = (boolean[]) obj;
                Boolean[] booleans = new Boolean[booleanArray.length];
                for (int i = 0; i < booleanArray.length; i++) {
                    booleans[i] = booleanArray[i];
                }
                jsonSerializer.serialize(booleans, writer);
                break;
        }
    }

}
