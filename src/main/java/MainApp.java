package main.java;

import java.util.ArrayList;
import java.util.List;

public class MainApp {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        JsonSerializer jsonSerializer = new JsonSerializer();

        /*IJsonMapper stringMapper = jsonSerializer.getMapper(String.class);
        stringMapper.write("helloWorld", jsonWriter);
        System.out.println(testString.toString());*/

        /*IJsonMapper numberMapper = new NumberMapper();
        numberMapper.write(6, jsonWriter);
        System.out.println(testString.toString());
*/
        List<Integer> collection = new ArrayList<>();
        collection.add(1);
        collection.add(-3);
        collection.add(6);
        collection.add(8);

        /*Map<Float, String> map = new HashMap<>();
        map.put(1.0f, "test1");
        map.put(2.0f, "test2");
        map.put(3.0f, "test3");
        map.put(4.0f, "test4");

        Map<Float, HashMap> map1 = new HashMap<>();
        map1.put(1.0f, new HashMap(map));
        map1.put(2.0f, new HashMap(map));
        map1.put(3.0f, new HashMap(map));
        map1.put(4.0f, new HashMap(map));*/

        jsonSerializer.setIndent(true, 3);

        Testobj testClass = new Testobj();

        Double[] array = {1.0, 2.0, 3.0};
        Character number = 1;
        Float number2 = 2.0f;

        System.out.println(jsonSerializer.serialize(testClass));
        System.out.println(jsonSerializer.serialize(number2));

        /*IJsonMapper mapMapper = new MapMapper();
        mapMapper.write(null, jsonWriter);
        System.out.println(testString.toString());*/

    }

}
