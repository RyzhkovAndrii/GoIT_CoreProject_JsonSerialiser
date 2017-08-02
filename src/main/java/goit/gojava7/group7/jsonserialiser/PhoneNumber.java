package goit.gojava7.group7.jsonserialiser;

public class PhoneNumber {

    public String type;
    public Long number;
    public int[] someData;

    public PhoneNumber(String type, Long number, int[] someData ) {
        this.type = type;
        this.number = number;
        this.someData = someData;
    }
}
