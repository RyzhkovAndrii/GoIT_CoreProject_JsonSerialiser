package goit.gojava7.group7.jsonserialiser;

public class Address {

    public String streetAddress;
    public String city;
    public String state;
    public int postalCode;

    public Address(String streetAddress, String city, String state, int postalCode) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

}
