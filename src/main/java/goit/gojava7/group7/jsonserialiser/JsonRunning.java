package goit.gojava7.group7.jsonserialiser;

import goit.gojava7.group7.jsonserialiser.serializer.JsonSerializer;

public class JsonRunning {

    public static void main(String[] args) {

        Address address = new Address("21\\2nd Street", "New York (\"NY\")", null, 10021);

        PhoneNumber[] phoneNumbers = {
                new PhoneNumber("home", 2125551234L, new int[] {1, 2, -4}),
                new PhoneNumber("fax",6465554567L, new int[] {0, -6, 100})};

        Person person = new Person("John", "Smith",
                25, address, phoneNumbers, true);

        JsonSerializer jsonSerializer = new JsonSerializer();
        jsonSerializer.setIndent(true, 3);

        String jsonString = jsonSerializer.serialize(person);
        System.out.println(jsonString);

    }

}
