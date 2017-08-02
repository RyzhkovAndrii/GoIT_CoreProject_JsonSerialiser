package goit.gojava7.group7.jsonserialiser;

import goit.gojava7.group7.jsonserialiser.annotation.JsonIgnore;
import goit.gojava7.group7.jsonserialiser.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public static String staticField = "This static field is ignored";

    public String firstName;

    @JsonProperty (name = "FamilyName")
    private String lastName;

    public transient int age;

    @JsonProperty
    public transient Address address;

    public PhoneNumber[] phoneNumbers;

    @JsonIgnore
    public String ignoreField = "This is ignored text";

    public boolean isAlive;

    public List<String> whatCanDo = new ArrayList<>();

    public Person(String firstName, String lastName, int age,
                  Address address, PhoneNumber[] phoneNumbers , boolean isAlive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.isAlive = isAlive;
        whatCanDo.add("breath");
        whatCanDo.add("eat");
        whatCanDo.add("walk");
    }
}
